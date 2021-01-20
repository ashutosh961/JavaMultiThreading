
public class SynchronizedSharedObject {

	private  volatile int count = 0;

	public synchronized void setCount(int c) {
		System.out.println("name:"+ Thread.currentThread().getName());
		System.out.println("Previous value:"+this.getCount());
		count = c;
		System.out.println("name:"+ Thread.currentThread().getName());
		System.out.println("Updated value:"+this.getCount());
	}
	
	public synchronized int getCount() {
		return count;
	}
	
	synchronized void test1(SynchronizedSharedObject s2)
    {
        System.out.println("test1-begin");
        try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
        // taking object lock of s2 enters
        // into test2 method
        s2.test2(this);
        System.out.println("test1-end");
    }
 
    // second synchronized method
    synchronized void test2(SynchronizedSharedObject s1)
    {
        System.out.println("test2-begin");
        try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
        // taking object lock of s1 enters
        // into test1 method
        s1.test1(this);
        System.out.println("test2-end");
    }
}
