package Semaphore;

public class BinarySemaphore implements SemaphoreInterface{

	boolean signal = false;

	@Override
	public  void acquire() {
		// TODO Auto-generated method stub

		signal = true;
		this.notify();
		
	}
	
	@Override
	public  void release() throws InterruptedException {
		// TODO Auto-generated method stub
		
		synchronized(this) {
			
			while(!signal) 	
				this.wait();
			
			signal = false;
		}
	}


}
