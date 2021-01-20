
public class RaceCondtion implements Runnable{

	
	SharedObject obj = null;

	public RaceCondtion(SharedObject obj){
		this.obj = obj;
	}

	public void run(){
		
		for(int i=1;i<11;i++) {
			
			obj.setCount(i);	
			
		}
	}

	
	public static void main(String[] args) {
		
		SharedObject sharedObj = new SharedObject();
		
		
		new Thread(new RaceCondtion(sharedObj)).start();
		new Thread(new RaceCondtion(sharedObj)).start();
		new Thread(new RaceCondtion(sharedObj)).start();
		new Thread(new RaceCondtion(sharedObj)).start();
		new Thread(new RaceCondtion(sharedObj)).start();
		new Thread(new RaceCondtion(sharedObj)).start();
	}
}