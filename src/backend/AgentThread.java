package backend;

import java.awt.Color;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.swing.SwingUtilities;

//why not extend thread
public class AgentThread implements Runnable{
	private AgentsGroup parent;
	private Agent iAgent;
	private ThreadsGroup threads;
	private boolean awake;
	private ExecutorService executor = Executors.newSingleThreadExecutor();
	private Future<?> publisher;
	private Color blueish = new Color (0, 156, 211);
	
	public AgentThread(AgentsGroup parent, Agent iAgent, ThreadsGroup threads) {
	    this.parent = parent;
	    this.iAgent = iAgent;
	    this.threads = threads;
	    this.awake = false;
	    
	}

	@Override
	public void run() {

//		while (getParent().isVisible() && !parent.isElectionComplete() && this.awake) {
		while (!Thread.currentThread().interrupted() && !parent.isElectionComplete()) {
				// Some small delay...
				// When they finish depends on how much they sleep

			// Repaint the agents pen...
			SwingUtilities.invokeLater(new Runnable() {
			    @Override
			    public void run() {
//				getParent().repaint();
			    	getParent().updateUI();
			    }
			});

			// DANGER : infectedCount is global but synchronized
			// -1 HERE
			if (parent.getBelievers() == parent.AGENT_COUNT-1){
				System.out.println("Thread "+iAgent.getAID() +" finished at round " + parent.roundsCount() + ", threads remaining: " + Thread.activeCount());
				parent.electionIsComplete();
//				threads.endThread(iAgent);
//				threads.stopElection();

			}
			else {
			// not every time they connect they can infect
				
				Agent b = findConnectionPartner();
				while (b == null){
					b = findConnectionPartner();
				}
				System.out.println("Agent " + iAgent.getAID() + " attemping to connect to agent "+ b.getAID());
			// DANGER : failed is global
				if (!iAgent.isEngaged()){
					b.engage();
					iAgent.engage();
				  	if (b.getLeaderAID() > iAgent.getLeaderAID()){
						iAgent.converted();
						iAgent.updateLeader(b.getLeaderAID());
						// b converted iAgent so only successful for b
						if (parent.leader().getAID() ==  iAgent.getLeaderAID())
							parent.belive(iAgent);
						parent.interactions.successful(b.getAID(), iAgent.getAID());
						parent.interactions.failed(iAgent.getAID(), b.getAID());
						System.out.println("	Agent " + iAgent.getAID() + " got infected by agent "+ b.getAID());
				  	}
					else if (b.getLeaderAID() < iAgent.getLeaderAID()){
						b.converted();
						b.updateLeader(iAgent.getAID());
						if (parent.leader().getAID() ==  b.getLeaderAID())
							parent.belive(b);
						//iAgent converted b
						parent.interactions.successful(iAgent.getAID(), b.getAID());
						parent.interactions.failed(b.getAID(), iAgent.getAID());
						System.out.println("	Agent " + iAgent.getAID() + " infected agent "+ b.getAID());
					}
					else {
						//b.getLeaderAID() == iAgent.getLeaderAID()
						b.metFollower();
						// none of them were successful
						parent.interactions.failed(b.getAID(), iAgent.getAID());
						parent.interactions.failed(iAgent.getAID(), b.getAID());
						System.out.println("	This interaction didn't change state of the agents");
						parent.failed(1);
					}
					b.disengage();
					iAgent.disengage();
				}
				else {
					System.out.println("	Agent is busy!");
					parent.failed(2);
				}
				// LOCK
				parent.rounds();
				if (iAgent.getLeaderAID() == parent.leader().getAID() && iAgent.getAID() != parent.leader().getAID())
					iAgent.setColor(blueish);
				
				if (b.getLeaderAID() == parent.leader().getAID() && b.getAID() != parent.leader().getAID())
					b.setColor(blueish);
				
				// Some small delay...
				// When they finish depends on how much they sleep
/*				try {
				    Thread.sleep(1000);
//				    this.notify();
				    putSleep();
				} catch (InterruptedException ex) {
				}*/
			}

		}

	}

	public AgentsGroup getParent() {
		parent.failed(2); 
	    return parent;
	}
	
	private Agent findConnectionPartner(){
		// not every time they connect they can infect
		
		Agent b = getParent().getAgents().get(UniqueRandomNumbers.random(parent.AGENT_COUNT-1));
		
		if (b.isEngaged() || (b.getAID() == iAgent.getAID())){
			return null;
		}
		else {
			return b;
		}
	}
	
	public synchronized void wakeUp() {
	    this.awake = true;
	}
	
	public synchronized void putSleep() {
	    this.awake = false;
	}
	
	public void start() {
		publisher = executor.submit(this);
	}

	public void pause() {
		publisher.cancel(true);
	}

	public void resume() {
		start();
	}

	public void stop() {
		executor.shutdownNow();
	}
	
}
