package Lock;

public class ReadWriteLockTester {

	ReadWriteLock readWriteLock  = new ReadWriteLock();
	int count = 0;
	static final int  READ = 0; 
	static final int  WRITE = 1;
	
	public static void main(String[] args) throws InterruptedException {
	
		
		
		ReadWriteLockTester test = new ReadWriteLockTester();
		
		Thread read1 = new Thread( () -> {

			test.criticalSection(READ);

		});
		
		Thread read2 = new Thread( () -> {
			
			test.criticalSection(READ);
			
		});
		
		Thread read3 = new Thread( () -> {
			
			test.criticalSection(READ);
			
		});
		
		Thread read4 = new Thread( () -> {
			
			test.criticalSection(READ);
			
		});
		
		Thread read5 = new Thread( () -> {
			
			test.criticalSection(READ);
			
		});

		
		Thread write1 = new Thread( () -> {

			test.criticalSection(WRITE);

		});
		
		Thread write2 = new Thread( () -> {
			
			test.criticalSection(WRITE);
			
		});
		
		Thread write3 = new Thread( () -> {
			
			test.criticalSection(WRITE);
			
		});
		
		read1.start();
		write1.start();
		write2.start();
		read2.start();
		read3.start();
		read4.start();
		write3.start();
		read5.start();
		
		read1.join();
		write1.join();
		write2.join();
		read2.join();
		read3.join();
		read4.join();
		write3.join();
		read5.join();
		
		System.out.println("Main Thread finished");
	}
	
	public  void criticalSection(int type) {
		
		lock(type);
			
		for(int i=0;i<10;i++) {
			
			if(type == WRITE) {
				count++;
				System.out.println("WriterThread id: "+ Thread.currentThread().getId() +" Count: "+count);
			}
			
			if (type == READ)
				System.out.println("ReaderThread id: "+ Thread.currentThread().getId() +" Count: "+count);

		}
		
		unlock(type);
	}	
	
	public void lock(int type) {
	
		if (type == READ)
			readWriteLock.lockRead();
		else if(type == WRITE)
			readWriteLock.lockWrite();
	}
	
	public void unlock(int type) {
		
		if (type == READ)
			readWriteLock.unlockRead();
		else if(type == WRITE)
			readWriteLock.unlockWrite();
	}
}
