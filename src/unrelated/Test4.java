/*http://handling-thread.blogspot.ca/2012/05/pause-and-resume-thread.html*/
package unrelated;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Test4 implements Runnable {

	private ExecutorService executor = Executors.newSingleThreadExecutor();
	private Future<?> publisher;
	protected volatile int counter;

	private void someJob() {
		System.out.println("Job Done :- " + counter);

	}

	@Override
	public void run() {
		Thread t = Thread.currentThread();
		while (!t.interrupted()) {
			System.out.println("Working ... \n");
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
}