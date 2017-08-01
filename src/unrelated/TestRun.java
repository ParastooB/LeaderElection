package unrelated;

public class TestRun {
	   public static void main(String[] args) throws InterruptedException{
	    	long startTime   = System.currentTimeMillis();
	        Test4 si = new Test4();
	        si.start();
	        try {
	           Thread.sleep(50);
	        } catch (InterruptedException x) { }
	        si.pause();
	        try {
		           Thread.sleep(3000);
		        } catch (InterruptedException x) { }
	        si.resume();
	        try {
		           Thread.sleep(50);
		        } catch (InterruptedException x) { }
	        si.stop();
		    long endTime   = System.currentTimeMillis();
		    long totalTime = endTime - startTime;
		    System.out.println(totalTime + "\n");
		    return;
	     }
}
