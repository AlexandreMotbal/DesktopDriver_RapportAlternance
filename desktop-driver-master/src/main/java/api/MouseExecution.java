package api;

public class MouseExecution {
	private final static int move = 0;
	private final static int click = 1;
	private final static int rightClick = 2;
	private final static int middleClick = 3;
	private final static int doubleClick = 4;
	private final static int down = 5;
	private final static int release = 6;
	private final static int wheel = 7;
	private final static int drag = 8;
	
	public MouseExecution(int type, String[] commandsData) {
		
	
		if(type == move) {
			System.out.println("Success : Record => move");
		}
		
		else if(type == click) {
			System.out.println("Success : Record => Click");
		}
		else if(type == rightClick) {
			System.out.println("Success : Record => rightClick");
		}
		else if(type == middleClick) {
			System.out.println("Success : Record => middleClick");
		}
		else if(type == doubleClick) {
			System.out.println("Success : Record => doubleClick");
		}
		else if(type == down) {
			System.out.println("Success : Record => down");
		}
		else if(type == release) {
			System.out.println("Success : Record => release");
		}
		else if(type == wheel) {
			System.out.println("Success : Record => Wheel");
		}
		else if(type == drag) {
			System.out.println("Success : Record => drag");
		}
		else {
			System.out.println("Error : Wrong subType");
		}
		
		
	}
}                            
