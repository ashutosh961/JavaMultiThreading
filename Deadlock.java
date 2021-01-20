
public class Deadlock  {

	public static void main(String[] args) {

		SynchronizedSharedObject s1 = new SynchronizedSharedObject();
		 
	    // creating second object
		SynchronizedSharedObject s2 = new SynchronizedSharedObject();
	 
	    // creating first thread and starting it
		DeadLockThread1 t1 = new DeadLockThread1(s1, s2);
		new Thread(t1).start();
	 
	    // creating second thread and starting it
	    DeadLockThread2 t2 = new DeadLockThread2(s1, s2);
	    new Thread(t2).start();
	    
	    try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
