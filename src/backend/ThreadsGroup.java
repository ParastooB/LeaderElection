package backend;

public class ThreadsGroup {
	
    ThreadGroup tg = new ThreadGroup ("1");
    
    public ThreadsGroup (AgentsGroup agents){
	    for (Agent agent : agents.getAgents()) {
	    	AgentThread gb = new AgentThread(agents,agent,this);
	        new Thread(tg, gb, "Thread for " + agent.getAID()).start();
	    }
    }
    
    public void StopElection(){
    	tg.destroy();
    }
}
