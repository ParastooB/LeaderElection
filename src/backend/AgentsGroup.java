package backend;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class AgentsGroup extends JPanel {

/**
	 * 
	 */
	// What?
	private static final long serialVersionUID = 1L;
	
// 	--------------------------	Simulation Parameters
	public final int AGENT_COUNT = 30;
	public static final int FrameSizeX = 1024;
	public static final int FrameSizeY = 1024;
//	--------------------------------------------------------
	
	private List<Agent> agentsList;
	
	// Robots who are aware of the true leader value
	private int believers= 0;
	
	// is this useful?
	private List<Agent> believerList = new ArrayList<Agent>(AGENT_COUNT);
	
	private int rounds = 0;
	private int failed = 0;
	private int repeated = 0;
	private boolean electionCompleted = false;
	
	Interactions interactions = new Interactions(AGENT_COUNT);

	public AgentsGroup() {
	    agentsList = new ArrayList<Agent>(AGENT_COUNT);
	    
	    int m = Math.min(FrameSizeX/2, FrameSizeY/2);
	    int r = 4 * m / 5;

	    for (int index = 0; index < AGENT_COUNT ; index++) {
	    	// ID's between 1000 and 10000
	    	ArrayList<Integer> IDlist = UniqueRandomNumbers.UniqueRandomNumber(1000, 10000, AGENT_COUNT);
	    	Agent agentNew = new Agent(IDlist.get(index));
	    	
// ------------------- Set the simulation ------------------------------------------------
			double t = 2 * Math.PI * index / AGENT_COUNT;
			int x = (int) Math.round(FrameSizeX/2 + r * Math.cos(t));
			int y = (int) Math.round(FrameSizeY/2 + r * Math.sin(t));

			Dimension size = agentNew.getSize();

			if (x + size.width > FrameSizeX) {
				x = FrameSizeX - size.width;
			}
			if (y + size.height > FrameSizeY) {
				y = FrameSizeY - size.height;
			}

			agentNew.setLocation(new Point(x, y));
			
			int s = 19 * m / 20;
			int xi = (int) Math.round(FrameSizeX/2 + s * Math.cos(t) - 1);
			int yi = (int) Math.round(FrameSizeY/2 + s * Math.sin(t) - 1);
			
			agentNew.setInfoLocation(new Point(xi,yi));
			agentsList.add(agentNew);
			// first column of both failed and succeeded interactions pages is the ID of agent
			interactions.initi(agentNew.getAID());
	    }
	    	
	    leader().setColor(new Color (211,37,0));

	}

// --------------------------------- Simulation Paint ---------------------------------
	@Override
	protected synchronized void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    Graphics2D g2d = (Graphics2D) g.create();
	    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    g2d.drawString("Rounds: "+String.valueOf(this.rounds), 100, 100);
	    g2d.drawString("Failed: "+String.valueOf(this.failed+this.repeated), 100, 120);
	    g2d.drawString("Believers: "+String.valueOf(this.getBelievers()), 100, 140);
	    for (Agent agent : agentsList) {
	    	agent.paint(g2d);
	    	ArrayList<Integer> a1SuccessList = interactions.getSuccessfulInteractions(agent.getAID());
	    	// Painting the connections
			for (int ID : a1SuccessList){
		    	Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{1}, 0);
		    	g2d.setStroke(dashed);
		    	g2d.setPaint(new Color(0,192,0));
		    	Agent iA = searchList(ID);
		    	g2d.drawLine(agent.getLocation().x, agent.getLocation().y, iA.getLocation().x, iA.getLocation().y);
			}
			ArrayList<Integer> a1FailList = interactions.getFailedInteractions(agent.getAID());
			for (int ID2 : a1FailList){
	        	Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
	        	g2d.setStroke(dashed);
	        	g2d.setPaint(new Color(192,192,192));
	        	Agent iA = searchList(ID2);
	        	g2d.drawLine(agent.getLocation().x, agent.getLocation().y, iA.getLocation().x, iA.getLocation().y);
			}
	    }
	    g2d.dispose();
	}

	public synchronized void usefulIneraction(){
		this.believers ++;
	}
	
	public synchronized void rounds(){
		this.rounds ++;
	}
	
	public synchronized void failed(int errorCode){
		if (errorCode == 1)
			this.repeated ++;
		else if (errorCode == 2)
			this.failed ++;
	}
	
	public synchronized void belive(Agent i){
		this.believerList.add(i);
			this.believers ++;
	}
	
	public int getBelievers(){
		int a = this.believers;
		return a;
	}
	
	public int roundsCount(){
		int a = this.rounds;
		return a;
	}
	
	public List<Agent> getAgents() {
	    return agentsList;
	}
	
	public boolean isElectionComplete() {
	    return this.electionCompleted;
	}
	
	public void electionIsComplete() {
	    this.electionCompleted = true;
	}

	public Agent leader(){
		Agent maxAG = this.agentsList.get(0);
		for (Agent agent : this.agentsList) {
			if (agent.getAID() > maxAG.getAID())
				maxAG = agent;
		}
		return maxAG;
	}
	
	private Agent searchList(int ID){
		Agent found = new Agent(-1);
		for (Agent agent : this.agentsList) {
			if (agent.getAID() == ID)
				found = agent;
		}
		return found;
	}

}
