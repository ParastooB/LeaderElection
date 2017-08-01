/*http://handling-thread.blogspot.ca/2012/05/pause-and-resume-thread.html*/
package unrelated;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public abstract class Test4 implements Runnable {

	private ExecutorService executor = Executors.newSingleThreadExecutor();
	private Future<?> publisher;
	protected volatile int counter;

	private void someJob() {
		System.out.println("Job Done :- " + counter);

	}

	abstract void task();

	@Override
	public void run() {
		Thread t = Thread.currentThread();
		while (!t.interrupted()) {
			task();
		}
	}

	public void start() {
		publisher = executor.submit(this);
	}

	public void pause() {
		counter = 100;
		publisher.cancel(true);
	}

	public void resume() {
		counter = 200;
		start();
	}

	public void stop() {
		counter = 300;
		executor.shutdownNow();
	}
	
    public static void main(String[] args) throws InterruptedException{
    	long startTime   = System.currentTimeMillis();
        Test2 si = new Test2();
        Thread t = new Thread(si);
        t.start();
        try {
           Thread.sleep(10);
        } catch (InterruptedException x) { }
        
	    long endTime   = System.currentTimeMillis();
	    long totalTime = endTime - startTime;
	    System.out.println(totalTime + "\n");
     }
}