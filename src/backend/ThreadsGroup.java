package backend;

import java.util.HashMap;

public class ThreadsGroup {
	
    private ThreadGroup tg = new ThreadGroup ("1");
   	private HashMap<Agent,Thread> m = new HashMap<Agent,Thread>();
    
    public ThreadsGroup (AgentsGroup agents){
	    for (Agent agent : agents.getAgents()) {
	    	AgentThread gb = new AgentThread(agents,agent,this);
	        this.m.put(agent,new Thread(tg, gb, "Thread for " + agent.getAID()));
	    }
    }
    
    public void startElection(){
	    for (Thread t : m.values()) {
	    	t.start();
	    }
    }
    
    public void startOne(Agent a){
    	this.m.get(a).start();
    }
    
    public void interuptElection(){
	    for (Thread t : m.values()) {
	    	t.interrupt();
	    }
    }
    
    public void interuptOne(Agent a){
    	this.m.get(a).interrupt();
    }
    
    public void waitForOne(Agent a) throws InterruptedException{
    	this.m.get(a).join();
    }
    
    public void endThread(Agent e){
    	this.m.remove(e);
    }
    
    public void stopElection(){
	    for (Thread t : m.values()) {
	    	t.destroy();
	    }
    }
    
    public void pauseElection(){
    	//tg.suspend();
    }
    
    public void resumeElection(){
    	//g.resume();
    }
}
