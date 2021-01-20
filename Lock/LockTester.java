package Lock;

public class LockTester {

	FairLock simpleLock  = new FairLock();
	FairReEntrantLock reEntrantLock =  new FairReEntrantLock();
	
	
	public static void main(String[] args) throws InterruptedException {
	
		boolean guard = true;
		boolean reEntrant = false;
		
		LockTester test = new LockTester();
		
		Thread t1 = new Thread( () -> { test.criticalSection(guard,reEntrant); }, "Thread1");
		
		Thread t2 = new Thread( () -> {	test.criticalSection(guard,reEntrant); }, "Thread2");
		
		Thread t3 = new Thread( () -> { test.criticalSection(guard,reEntrant); }, "Thread3");
		
		Thread t4 = new Thread( () -> { test.criticalSection(guard,reEntrant); }, "Thread4");
		
		Thread t5 = new Thread( () -> {	test.criticalSection(guard,reEntrant); }, "Thread5");
		
		Thread t6 = new Thread( () -> { test.criticalSection(guard,reEntrant); }, "Thread6");
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		
		t1.join();
		t2.join();
		t3.join();
		t4.join();
		t5.join();
		t6.join();

		
		System.out.println("Main Thread finished");
	}
	
	public  void criticalSection(boolean guard,boolean reEntrant) {
		
		lock(guard,reEntrant);
			
		for(int i=0;i<10;i++) {
			
			System.out.println("Thread name: "+ Thread.currentThread().getName() +" Task "+i);
			
			if(reEntrant)
				secondCriticalSection(guard,reEntrant);
		}
		
		unlock(guard,reEntrant);
	}
	
	public  void secondCriticalSection(boolean guard, boolean reEntrant) {
		
		lock(guard,reEntrant);

		System.out.println("Thread name: "+ Thread.currentThread().getName() +" Thread priority "+ Thread.currentThread().getPriority());
		
		unlock(guard,reEntrant);
	}
	
	public void lock(boolean guard, boolean reEntrant) {
		
		if(guard && !reEntrant )
			simpleLock.lock();
		else if(guard && reEntrant )
			reEntrantLock.lock();
	}
	
	public void unlock(boolean guard, boolean reEntrant) {
		
		if(guard && !reEntrant )
			simpleLock.unlock();
		else if(guard && reEntrant )
			reEntrantLock.unlock();
	}
	
}
