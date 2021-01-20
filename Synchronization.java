
public class Synchronization  implements Runnable {

	SynchronizedSharedObject obj = null;

	public Synchronization(SynchronizedSharedObject obj){
		this.obj = obj;
	}

	public void run(){
		
		for(int i=1;i<11;i++) {
			
			obj.setCount(i);	
					
		}
	}

	
	public static void main(String[] args) {
		
		SynchronizedSharedObject sharedObj = new SynchronizedSharedObject();
		
		
		new Thread(new Synchronization(sharedObj)).start();
		new Thread(new Synchronization(sharedObj)).start();
		new Thread(new Synchronization(sharedObj)).start();
		new Thread(new Synchronization(sharedObj)).start();
		new Thread(new Synchronization(sharedObj)).start();
		new Thread(new Synchronization(sharedObj)).start();
	}
}