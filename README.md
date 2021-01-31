Java Multithreading:
-------------------

What is a thread ?
-> A thread is a lightweight process. It is different path of execution of the same program.
-> It can be used to run multiple tasks of a same program in parallel.

How do you create threads in Java ?
-> You can create threads in Java either by extending the Thread class or by implementing runnable interface.
-> As you can only extend a class once, the runnable interface implementation is the preferred method.

How do you create a thread with Runnable interface ?
->
	Runnable runnable = new Runnable() { 
			public void run()
				{	System.out.println("Thread started");
				} 			
	};
	
	Thread thread1 = new Thread(runnable);
	thread1.start();
	
	Thread thread2 = new Thread( new Runnable() { 
			public void run()
				{	System.out.println("Better thread started");
				} 
			});
	thread2.start();
	
	Thread thread3 = new Thread( () -> { System.out.println("Best thread started"); );
	thread4.start();
	
What are the different states of thread life cycle ?
-> 
	1) New
	The thread is in new state if you create an instance of Thread class but before the invocation of start() method.

	2) Runnable
	The thread is in runnable state after invocation of start() method, but the thread scheduler has not selected it to be the running thread.

	3) Running
	The thread is in running state if the thread scheduler has selected it.

	4) Non-Runnable (Blocked)
	This is the state when the thread is still alive, but is currently not eligible to run.

	5) Terminated
	A thread is in terminated or dead state when its run() method exits.

What is a critical section ?
-> When multiple threads try to access a shared resource, then that resource or the section of code is called critical section.
	
What are the common problems with multi-threading while trying to access a shared resource ?
->	RaceCondition
	DeadLock
	Starvation
	LiveLock
	
	
What is a Race Condition ?
-> 	When two or more threads are trying to access a shared resource. They will race with each other to acquire the resource. This might 
	cause the resource to be corrupted or both threads reading and writing incorrect values to the resource.

What are two scenarios under which a race condition occurs ?
-> The two scenarios under which race conditions occur are "Check then act" and "read-modify-write".

	-> check-then-act: 
	Usually the value of a variable is checked and then an action is taken. Without proper synchronization, the resulting code can have a race condition. An example is below:

        Object myObject = null;
        if (myObject == null) {
            myObject = new Object();
        }
	
	-> read-modify-write: For instance, whenever a counter variable is increment, the old state of the counter undergoes a transformation to a new state. 
	Without proper synchronization guards, the counter increment operation can become a race condition.

How do we prevent a Race condition ?
-> 	A race condition can be prevented by synchronizing access to the shared resource between threads. Only one thread can operate on the shared resource
	at a time and all the other threads have to wait. Once the thread exits the critical section, other threads can contend for the critical resource.
	
How do we achieve synchronization between threads trying to access shared resource ?
-> 	Synchronization between threads can be achieved by using mutex, semaphores and monitors. 

How do Mutex, Semaphores and Monitors achieve thread synchronization ?
-> 	They achieve thread synchronization by blocking and awakening threads contending for resources. Monitors and mutexes provide exclusive access to threads to 
	critical sections whereas Semaphores give permits to threads to enter critical section.

How can block threads in java ?
-> 	We can block a thread on a shared object by using wait() method.

How can awaken a waiting thread in java ?
-> 	We can awaken a waiting thread on a shared object by using notify() method.

How can awaken all waiting thread in java ?
-> 	We can awaken all waiting thread on a shared object by using notifyAll() method.
	
Where can I find wait, notify and notifyAll methods in java ?
-> 	All objects in java are supplied with wait(), notify() and notifyAll() methods in Java.

How can we achieve inter thread communication for thread synchronization in Java?
-> 	We can achieve inter thread communication for thread synchronization using wait(), notify() and notifyAll() methods in Java.
	
What is a Mutex ?
-> 	A mutex is locking mechanism. The thread that holds the mutex is allowed to access the critical section. The threads which dont have the mutex cannot
	access the critical section. The thread needs to release the mutex once it exits the critical section so that other threads can enter the critical
	section.

What is a Semaphore ?
-> 	A semaphore is mechanism which gives permits to the threads. It has two methods acquire and release. Permits are issued to thread on calling acquired.
	Threads are supposed to release permits on calling release. The threads do not lock when they receive permits. If a thread tries to acquire permits after
	the maximum permits have been issued then it needs to wait. Alos ff a thread tries to release and no permits have been issued so far then it needs to wait.

What are the different types of Semaphores ?
-> 	Binary Semaphore - It only issues 1 permit. Hence it is called binary semaphore.
	Counting Semaphore - It only has a lowerbound but doesnt have an upper bound on the number of permits.
	Bounder Semaphore - It has a lower and an upper bound on number of permits being issued.

What is a Monitor ?
-> 	Mutex along with a condition check constitute a monitor. The best example is the "synchronized" keyword in java. The thread holding  the monitor is allowed to
	enter the critical section. The thread that dont hold the monitor are blocked. The thread exiting the critical section has to release the monitor.
	
Which are the higher level synchronization constructs ?
-> Monitor is a higher level synchronization construct.

Which are the lower level synchronization constructs ?
-> Sempahores and Monitors are lower level synchronization constructs.

What problems need to be avoided while designing monitors ?
->  Missed Signals
	Busy Waiting 
	Slipped Condition
	Spurious Wakeup

How are monitors implemented in Java ?
->  - 	We must design a mechanism which allows a thread to obtain a lock while entering critical section. All other threads need to be blocked. The thread in the critical	
		section must release the monitor on exit.
	- 	Ideally threads can communicate by setting boolean flag for certain shared variables. In that case the first thread will acquire the critical resource and set a boolean flag.
		The waiting thread will then keep on checking the status of the flag in a while loop. 
	- 	As you can imagine this mechanism will waste lot of CPU time and hence is known as busy waiting.
	- 	Thus we can make clever use of wait(), notify() and notifyAll() to achieve this locking mechanism.
	- 	There can be a possibility that a notify() is called before a wait(). In that case the waiting thread will never receive notify signal and will be starved.
		This is called a missed signal. To avoid this we will use a boolean flag in the monitor to set and reset locking.
	- 	There are also situations where a waiting thread may wake up automatically without the call of notify(). This is called spurious wakeup.
		This can be prevented by using a while loop instead of a if condition while checking value of lock. Such a lock is called Spin Lock.

Code implementation 
->	Monitors are mostly implemented a spin lock.
	
	// prevents missed signals
	boolean isLocked;

	public Monitor(){
		isLocked = false;
	}

	public void lock() {
		
		synchronized(this) {			
	
			// prevents spurious wakeups
			while(!isLocked) {
				// prevents busy waiting
				this.wait();
			}
			isLocked = false;
		}
	}
	
	public void unlock() {
		
		synchronized(this) {
			isLocked = true;
			this.notify();
		}
	}
	
Why are spin locks used ?
->	Spin locks are used to prevent spurious wake-ups. Due to use of while loop we check again after a wakeup if the condition of lock is set to false.
	If its a spurious wakeup then the condition would be true.

What is a nested monitor lockout ?
->	If we enter a method which acquires a monitor which further calls another method which also acquires a monitor. Then in such a case we will enter a condition
	nested monitor lockout and the thread will block. This is different from deadlock.
	
How do we prevent nested monitor lockout ?
->	We can prevent nested monitor lockout by using re entrant locks. This is a lock which can lock itself.

What are Re Entrant locks ?
-> The locks which can lock itself n number of times is called a reEntrant lock.

	boolean isLocked = false;
	Thread lockedBy = null;
	int lockedCount = 0;
	
	public synchronized void lock() {
			
			Thread currentThread = Thread.currentThread();
			
			while(isLocked && currentThread != lockedBy ) {
				this.wait();
			}
			
			isLocked = true;
			lockedBy = currentThread;
			lockedCount++;
	}

	public synchronized void unlock() {
			
			if(Thread.currentThread() == lockedBy) {
				
				lockedCount--;
				
				if(lockedCount == 0) {
					isLocked = false;
					this.notifyAll();
				}
			}
	}
	
-> 	Here we can see that we need to check if the lock is being locked by the same thread. If yes then we need to increment the lockCounter. We will decrement this lockCount during 
	unlock if the same locking thread issues the unlock request.

What is a deadlock ?
-> 	If two threads are trying to acquire two locks. And if each other is waiting on thread to release the lock then we enter a deadlock condition.

How do we prevent a deadlock ?
-> 	The locks need to be ordered in such a way that the acquiring threads dont get into a deadlock.

How can we detect a deadlock ?
-> 	If the graph generated by locks and threads creates a cycle then we have a deadlock. Course schedule(leetcode) is the example of deadlock detection.

What is a cyclic barrier ?
-> 	A cyclic barrier is a point in code where an incoming thread blocks. The threads are only released once all threads reach that point in code. Hence it acts a barrier. Each cyclic
	barrier has a count which gets reset once all threads have crossed the barrier and can be used again for next incoming threads. Hence it is called cyclic barrier.
	
What is a count down latch ?
-> 	A count latch mechanism which has two methods of await() and countdown(). A latch is always initiated with a number. Lets say we have a countdown latch of 2. When we call latch.await(), 
	all the threads will block. The blocked threads will stay blocked till other threads call latch.countdown(). Since we have a latch of 2. countdown() needs to be called twice to 
	unlatch. At this point all the blocked threads are released.
	
What is a Producer Consumer problem ?
-> 	This is a classic Computer Science problem. We have multiple producer and consumer threads that are producing and consumer tasks using buffer. 
	Depending on the size of the buffer we have different blocking and non blocking implementations.
	
	1. 	Bounded Buffer: 
		- Here the size of the buffer is bounded.
		- This is typically used in application where the memory is limited.
		- Since its a bounded buffer we can use an array as a queue.
		- Once the queue becomes full the producer threads have to be blocked.
		- Once the queue becomes empty the consumer threads have to be blocked.

	2.	UnBounded Buffer
		- Here the size of the buffer is not bounded.
		- This is typically used for application where there are high amount of consumers.
		- Since there are no bounds we can use a linked list instead of an array.
		- Once the queue becomes empty the consumer threads have to be blocked.
		- The producer threads are never blocked and can keep on enqueuing requests on the queue.
		- If the memory exhausts then JVM will throw out of memory error.
		
JAVA concurrency:

Which are the three ways to achieve parallelism?

1. Parallel workers
Advantage: Easier to implement
Disadvantage: More care has to be taken if shared state is maintained.

2. Reactive or Event driven using Actors or Channels. Also known as Assembly line
Advantage: There is no shared state so concurrency problems are minimum.
Disadvantage: Difficult to implement program in the form of callbacks

3. Functional parallelism
This is very new

what is a same threaded system ?
It is a system which has one thread per core on a cpu. Each thread has its own data. The data is not shared between threads. Threads communicate with each other using message passing
