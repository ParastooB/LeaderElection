package unrelated;

public class Test3 {
	
    private static Thread listen;

    public static void listen() {
        listen = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                	//System.out.println("Waiting for message \n");
                    if (!Thread.interrupted()){
                    	//System.out.println("Did not get interupted yet\n");
                    }
                }
            }
        });
        listen.start();
    }

    public static void stopListen() {
        listen.interrupt();
    }
    
    public static void main(String[] args) throws InterruptedException{
    	long startTime   = System.currentTimeMillis();
        listen();
        try {
           Thread.sleep(1000);
        } catch (InterruptedException x) { }
        stopListen();
	    long endTime   = System.currentTimeMillis();
	    long totalTime = endTime - startTime;
	    System.out.println(totalTime + "\n");
	    
        listen();
        try {
           Thread.sleep(1000);
        } catch (InterruptedException x) { }
        stopListen();
	    endTime   = System.currentTimeMillis();
	    totalTime = endTime - startTime;
	    System.out.println(totalTime + "\n");
     }
}