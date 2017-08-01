package unrelated;

public class TestRun {
	   public static void main(String[] args) throws InterruptedException{
	    	long startTime   = System.currentTimeMillis();
	        Test4 si = new Test4();
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
