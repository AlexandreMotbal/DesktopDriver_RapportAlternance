package api;

public class KeyboardExecution {
	private final static int clear = 0;
	private final static int enter = 1;
	private final static int down = 2;
	private final static int release = 3;
	
	public KeyboardExecution(int type, String[] commandsData) {
		if(type == clear){
			System.out.println("Success : Record => clear");
			}
		else {
			if(type == enter) {
				System.out.println("Success : Record => enter");
			}
			else if(type == down) {
				System.out.println("Success : Record => down");
			}
			else if(type == release) {
				System.out.println("Success : Record => release");
			}
			else {
				System.out.println("Error : Wrong subType !");
			}
		}
	}
}
