package server;


import java.util.concurrent.TimeUnit;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class AppServer {  
	public static void launch() throws Exception {
    	int port =9988;
    	int ttl = 0;
    	int ttlMax = 1;
    	int timeBtw = 1;
    	
    	System.out.println("Port selectionn� :"+port);
    	while(ttl<ttlMax) {
    		System.out.println("essaie num�ro : "+ttl);
			try {
				new AppServer().run(port);  
			}
			catch(Exception e) {
		    	System.out.println(e);
				ttl = ttl+1;
			}
			TimeUnit.SECONDS.sleep(timeBtw);
		}
    	System.out.println("Rien � faire le port "+ port +" n'est pas disponible");
		}	
    public void run(int port) throws Exception{  
  
        // Create the multithreaded event loops for the server
        EventLoopGroup bossGroup = new NioEventLoopGroup();  
        EventLoopGroup workerGroup = new NioEventLoopGroup();  
  
        try {  
            // A helper class that simplifies server configuration           
            ServerBootstrap httpBootstrap = new ServerBootstrap();  
            
            // Configure the server
            httpBootstrap.group(bossGroup, workerGroup)  
                .channel(NioServerSocketChannel.class)  
                .childHandler(new ServerInitializer()) // <-- Our handler created here  
                .option(ChannelOption.SO_BACKLOG, 128)  
                .childOption(ChannelOption.SO_KEEPALIVE, true);  
   
            // Bind and start to accept incoming connections.  
            ChannelFuture httpChannel = httpBootstrap.bind(port).sync(); 
            System.out.println("Le serveur est en route");
            // Wait until server socket is closed
            httpChannel.channel().closeFuture().sync();  
        }
        finally {  
            workerGroup.shutdownGracefully();  
            bossGroup.shutdownGracefully();  
        }
    }  
}
