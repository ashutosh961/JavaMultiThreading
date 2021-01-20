public class DeadLockThread2 implements Runnable{

	private SynchronizedSharedObject s1;
    private SynchronizedSharedObject s2;
    
	DeadLockThread2(SynchronizedSharedObject s1,SynchronizedSharedObject s2){
		 this.s1 = s1;
	     this.s2 = s2;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		 s2.test2(s1);

	}
}