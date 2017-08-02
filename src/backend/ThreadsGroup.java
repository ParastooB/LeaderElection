package backend;

import java.util.HashMap;

public class ThreadsGroup {
	
    private ThreadGroup tg = new ThreadGroup ("1");
   	private HashMap<Agent,Thread> m = new HashMap<Agent,Thread>();
   	private HashMap<Agent,AgentThread> agentThreads = new HashMap<Agent,AgentThread>();
    
    public ThreadsGroup (AgentsGroup agents){
	    for (Agent agent : agents.getAgents()) {
	    	AgentThread gb = new AgentThread(agents,agent,this);
	    	this.agentThreads.put(agent, gb);
//	        this.m.put(agent,new Thread(tg, gb, "Thread for " + agent.getAID()));
	    }
    }
    
    public void startElection(){
/*	    for (Thread t : m.values()) {
	    	t.start();
	    }*/
	    for (AgentThread t : agentThreads.values()) {
	    	t.start();
	    }
    }
    
    public void startOne(Agent a){
//    	this.m.get(a).start();
    	this.agentThreads.get(a).start();
    }
    
    public void interuptElection(){
/*	    for (Thread t : m.values()) {
	    	t.interrupt();
	    }*/
	    for (AgentThread t : agentThreads.values()) {
	    	t.pause();
	    }
    }
    
    public void interuptOne(Agent a){
    	this.agentThreads.get(a).pause();
    }
    
    public void waitForOne(Agent a) throws InterruptedException{
    	this.m.get(a).join();
    }
    
    public void resumeOne(Agent a){
    	this.agentThreads.get(a).resume();
    }
    
    public void endThread(Agent e){
    	this.agentThreads.get(e).stop();
    	//this.agentThreads.remove(e);
    }
    
    public void stopElection(){
	    for (AgentThread t : this.agentThreads.values()) {
	    	t.stop();
	    }
    }
    
    public void pauseElection(){
	    for (AgentThread t : this.agentThreads.values()) {
	    	t.pause();
	    }
    }
    
    public void resumeElection(){
	    for (AgentThread t : this.agentThreads.values()) {
	    	t.resume();
	    }
    }
}
