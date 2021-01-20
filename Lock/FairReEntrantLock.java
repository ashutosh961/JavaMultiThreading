package Lock;

import java.util.*;

public class FairReEntrantLock implements LockInterface{

	Queue<SimpleLock> waitQueue = new LinkedList<>();

	boolean locked = false;
	Thread lockingThread = null;
	int lockCount = 0;

	
	@Override
	public void lock() {
		// TODO Auto-generated method stub
		
		SimpleLock waitLock = new SimpleLock();
		
		if(lockingThread == Thread.currentThread()) {
			lockCount++;
			return;
		}
		
		synchronized(this) {
		
			System.out.println("Lock requested by Thread name: "+ Thread.currentThread().getName()+ " | lock hashcode: "+ waitLock.hashCode());
			waitQueue.add(waitLock);	
		
		}
			
		while(locked || waitLock != waitQueue.peek()) {
			
			waitLock.lock();
		}
		
		synchronized(this) {
			
				lockingThread = Thread.currentThread();
				locked = true;
				lockCount++;
				System.out.println("Lock obtained by Thread name: "+ Thread.currentThread().getName());
				waitQueue.poll();
		}
		
	}

	@Override
	public void unlock() {
		// TODO Auto-generated method stub
		
		if(locked && lockingThread == Thread.currentThread()) {
			
			synchronized(this) {
				
				lockCount--;
				
				if(lockCount == 0) {
					locked = false;
					lockingThread = null;
					
					System.out.println("Threads waiting: "+ waitQueue.size());
					
					if(!waitQueue.isEmpty()) {
						
						SimpleLock waitLock = waitQueue.peek();
						System.out.println("Unlocking Thread name: "+ Thread.currentThread().getName()+ " | lock hashcode: "+ waitLock.hashCode());				
						waitLock.unlock();
		
					}
				}
			}
		}
	}
	
}
