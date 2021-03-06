package server;

import java.io.IOException;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;

public class ServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {  
    
  @Override  
  protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) throws IOException { 
	  String u = msg.uri();
	  new RequestAnalyser(u);
	  System.out.println(ctx.channel().remoteAddress().toString());
      ByteBuf content = Unpooled.copiedBuffer("Hello World!", CharsetUtil.UTF_8);  
      FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);  
      response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html");  
      response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());  
      ctx.write(response);  
      ctx.flush();  
  }  
    
}