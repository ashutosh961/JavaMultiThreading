package Semaphore;

public class CountingSemaphore implements SemaphoreInterface {

	int count = 0;
	
	@Override
	public void acquire() {
		// TODO Auto-generated method stub
		
		synchronized(this) {
			count++;
			this.notify();
		}
	}

	@Override
	public void release() throws InterruptedException {
		// TODO Auto-generated method stub
		
		synchronized(this) {
			
			while(count == 0) {
				this.wait();
			}
				
			count--;
			
		}
	}

	
}
