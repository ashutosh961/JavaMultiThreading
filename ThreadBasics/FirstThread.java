package ThreadBasics;

public class FirstThread implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		System.out.println("Thread started");
	}

	
	public static void main(String[] args) {
		
		
		
		FirstThread obj = new FirstThread();
		Thread thread = new Thread(obj);
		thread.start();
		thread.interrupt();
	}
}


