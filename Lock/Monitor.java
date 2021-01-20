package Lock;

public class Monitor implements LockInterface {

	boolean isLocked;
	
	public Monitor(){

		isLocked = false;
	}
	
	public void lock() {
		
		synchronized(this) {
			
			while(!isLocked) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			isLocked = false;
		}
	}
	
	public void unlock() {
		
		synchronized(this) {
			
			isLocked = true;
			this.notify();
		}
	}
}
