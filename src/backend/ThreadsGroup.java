package backend;

import java.util.ArrayList;

public class ThreadsGroup {
	
    ThreadGroup tg = new ThreadGroup ("1");
    ArrayList<Thread> m = new ArrayList<Thread>();
    
    public ThreadsGroup (AgentsGroup agents){
	    for (Agent agent : agents.getAgents()) {
	    	AgentThread gb = new AgentThread(agents,agent,this);
	        m.add(new Thread(tg, gb, "Thread for " + agent.getAID()));
	    }
    }
    
    public void startElection(){
	    for (Thread t : m) {
	    	t.start();
	    }
    }
    
    public void interuptElection(){
	    for (Thread t : m) {
	    	t.interrupt();
	    }
    }
    
    public void endThread(AgentThread e){
    	m.remove(e);
    }
    
    public void stopElection(){
	    for (Thread t : m) {
	    	t.destroy();
	    }
    }
    
    public void pauseElection(){
    	tg.suspend();
    }
    
    public void resumeElection(){
    	tg.resume();
    }
}
