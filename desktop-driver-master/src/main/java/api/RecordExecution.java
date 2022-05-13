package api;

public class RecordExecution {
	private final static int stop = 0;
	private final static int screenshot = 1;
	private final static int start = 2;
	private final static int create = 3;
	private final static int image = 4;
	private final static int value = 5;
	private final static int data = 6;
	private final static int status = 7;
	private final static int element = 8;
	private final static int position = 9;
	private final static int download = 10;
	private final static int imageMobile = 11;
	private final static int createMobile = 12;
	private final static int screenshotMobile = 13;
	private final static int summary = 14;
	
	public RecordExecution(int type, String[] commandsData) {
		if(type ==stop) {
			System.out.println("Success : Record => stop");
		}
		else if(type == download) {
			System.out.println("Success : Record => download");
		}
		else if(type == summary) {
			System.out.println("Success : Record => summary");
		}
		else {
			if(type == screenshot) {
				System.out.println("Success : Record => screnshot");
			}
			else if(type == screenshotMobile) {
				System.out.println("Success : Record => screenshotmobile");
			}
			else if(type == start) {
				System.out.println("Success : Record => start");
			}
			else if(type == create) {
				System.out.println("Success : Record => create");
			}
			else if(type == createMobile) {
				System.out.println("Success : Record => createmobile");
			}
			else if(type == image) {
				System.out.println("Success : Record => image");
			}
			else if(type == imageMobile) {
				System.out.println("Success : Record => imagemobile");
			}
			else if(type == value) {
				System.out.println("Success : Record => value");
			}
			else if(type == data) {
				System.out.println("Success : Record => data");
			}
			else if(type == status) {
				System.out.println("Success : Record => status");
			}
			else if(type == element) {
				System.out.println("Success : Record => element");
			}
			else if(type == position) {
				System.out.println("Success : Record => position");
			}
			else{
				System.out.println("Error : Wrong subType !");
			}
		}
	}
}
