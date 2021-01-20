package Lock;

public class ReEntrantLock implements LockInterface {

	boolean isLocked = false;
	Thread lockedBy = null;
	int lockedCount = 0;
	
	@Override
	public void lock() {
		// TODO Auto-generated method stub
		
		synchronized(this) {
			
			Thread currentThread = Thread.currentThread();
			
			while(isLocked && currentThread != lockedBy ) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			isLocked = true;
			lockedBy = currentThread;
			lockedCount++;
		}
	}

	@Override
	public void unlock() {
		// TODO Auto-generated method stub
		
		synchronized(this) {
			
			if(Thread.currentThread() == lockedBy) {
				
				lockedCount--;
				
				if(lockedCount == 0) {
					isLocked = false;
					this.notifyAll();
				}
			}
		}
	}

}
