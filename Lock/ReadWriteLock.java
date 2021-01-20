package Lock;

public class ReadWriteLock {

	int readers = 0;
	int writers = 0;
	int wtiteRequests = 0;
	
	public synchronized void lockRead() {
		
		while(writers > 0 || wtiteRequests > 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		readers++;
		
	}
	
	public synchronized void unlockRead() {
		
		readers--;
		notifyAll();
		
	}
	
	public synchronized void lockWrite() {
		
		wtiteRequests++;
		
		while( readers >0 || writers >0 ) {
			
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		wtiteRequests--;
		writers++;
	}
	
	public synchronized void unlockWrite() {
		
		writers--;
		notifyAll();
	}
}
