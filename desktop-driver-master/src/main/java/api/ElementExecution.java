package api;

public class ElementExecution{
	
	private final static int childs = 0;
	private final static int parents = 1;
	private final static int find = 2;
	private final static int Attributes = 3;
	private final static int select = 4;
	private final static int fromPoint= 5;
	private final static int script = 6;
	private final static int root= 7;
	private final static int loadTree = 8;
	private final static int listItems= 9;
	private final static int dialogBox= 10;
	private final static int focus = 11;
	
	public ElementExecution(int type,String[] commandsData){
		if(type == find) {
			System.out.println("Success : Driver => find");
		}
		else if(type == loadTree) {
			System.out.println("Success : Driver => LoadTree");
		}
		else if(type == fromPoint) {
			System.out.println("Success : Driver => fromPoint");
		}
		else if(type == dialogBox) {
			System.out.println("Success : Driver => DialogBox");
		}
		else {
			if(type == parents) {
				System.out.println("Success : Driver => Parents");	
			}
			else if(type == root) {
				System.out.println("Success : Driver => Root");
			}
			else if(type == Attributes) {
				System.out.println("Success : Driver => Attributes");
			}
			else if(type == script) {
				System.out.println("Success : Driver => Script");
			}
			else if(type == listItems) {
				System.out.println("Success : Driver => ListItems");
			}
			else if(type == focus) {
				System.out.println("Success : Driver => Focus");
			}
			else {
				if(type == childs) {
					System.out.println("Success : Record => childs");
				}
				
				else if(type == select) {
					System.out.println("Success : Driver => Select");
				}
				
				else {
					System.out.println("Error : Wrong subtype");
				}
			}
			
		}
	}
}
