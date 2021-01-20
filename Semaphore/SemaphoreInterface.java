package Semaphore;

public interface SemaphoreInterface {

	public void acquire();
	
	public void release() throws InterruptedException;
}
