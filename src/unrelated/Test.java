// Source : https://praveer09.github.io/technology/2015/12/06/understanding-thread-interruption-in-java/
package unrelated;

public class Test {
	
	public static void main(String[] args) throws InterruptedException {
/*		long startTime   = System.currentTimeMillis();
	    Thread taskThread = new Thread(taskThatFinishesEarlyOnInterruption());
	    taskThread.start();      // requirement 3
	    Thread.sleep(5_000);     // requirement 4
	    taskThread.interrupt();  // requirement 5
	    taskThread.join(1_000);  // requirement 6
	    long endTime   = System.currentTimeMillis();
	    long totalTime = endTime - startTime;
	    System.out.println("\n"+totalTime);*/
	    
	    Thread t5 = new Thread(taskThatPrintsEveryTimeInteruppted());
	    t5.start();
	    for(int i =0; i<5 ;i++){
	    	System.out.println(" Bye");
	    	
	    }
	    t5.join();
	    
	}

	private static Runnable taskThatFinishesEarlyOnInterruption() {
	    return () -> {
	        for (int i = 1; i < 110; i++) {
	            System.out.print(i);      // requirement 1
	            try {
	                Thread.sleep(1_000);  // requirement 2
	            } catch (InterruptedException e) {
	                break;                // requirement 7
	            }
	        }
	    };
	}
	
	private static Runnable taskThatPrintsEveryTimeInteruppted() {
	    return () -> {
	            try {
	            	System.out.println(" Sleeping");
	                Thread.sleep(10_000);  // requirement 2
	            } catch (InterruptedException e) {
	            	System.out.println(" Hey");
	            }
	    };
	}


}
