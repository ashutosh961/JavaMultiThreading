package Semaphore;

public class BoundedSemaphore implements SemaphoreInterface {

	int count = 0;
	int max ;
	
	public BoundedSemaphore(int max) {
		this.max = max;
	}
	
	@Override
	public void acquire() {
		// TODO Auto-generated method stub
		
		synchronized(this) {
			 while(count == max)
				try {
					this.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 
			 count++;
			 this.notify();
		}
	}

	@Override
	public void release() throws InterruptedException {
		// TODO Auto-generated method stub
		
		synchronized(this) {
			
			 while(count == 0)
				this.wait();
				 
			 	count--;
			 	this.notify();
		}
	}
}
