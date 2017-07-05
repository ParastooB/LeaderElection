/**
 * 
 */
package backend;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
/*
 * Agent.java
 * LeaderElection
 *
 * Copyright (C) 2014  beltex <https://github.com/beltex>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 * 
 * modified by Parastoo <https://github.com/parastoob> 2017
 */
public class Agent {

    /**
     * Agent unique identifier
     */
    private int AID;


    /**
     * Who do I believe is the leader? Myself by default (starting)
     */
    private int leaderAID;


    /**
     * Number of agents THIS agent has converted (infected) with it's leaderAID.
     * XOR with metFollowers
     */
    private int conversions;


    /**
     * How many agents has THIS agent interacted with who are following the
     * same leader. XOR with conversions
     * Just equal or same true leader?
     */
    private int metFollowers;


    /**
     * Am I the leader? False by default (starting). True if the election
     * completion estimate equation evaluates to true
     */
    private boolean isLeader;


    /**
     * Is the election complete? False by default (starting)
     */
    private boolean electionComplete;
    
// --------------------- Simulation Parameters ------------    
	private Color color;
	private Point location;
	private Point infoLocation;
	private Dimension size;
	private boolean busy = false;
// -----------------------------------------------------------

//	----------		CONSTRUCTOR		----------
    /**
     * Create an Agent. Sets leader ID to its own AID, conversion and met
     * follower counters to 0, and finally "is leader" and "election complete"
     * flags to false.
     *
     * @param AID Unique Agent ID (must be >= 0 & <= numAgents - 1)
     */
    public Agent(int AID) {
        this.AID = AID;
        this.leaderAID = AID;

        conversions = 0;
        metFollowers = 0;

        isLeader = false;
        electionComplete = false;
        
	    this.color = Color.GREEN;
	    size = new Dimension(25, 25);
    }


//	---------		PUBLIC METHODS		---------


    /**
     * Determine if this agent is equal to another
     *
     * @param agent Agent to compare with
     * @return True if all attributes equal, false otherwise
     */
    public Boolean equals(Agent agent) {
        if (agent != null &&
            AID == agent.AID &&
            leaderAID == agent.leaderAID &&
            conversions == agent.conversions &&
            metFollowers == agent.metFollowers &&
            isLeader == agent.isLeader &&
            electionComplete == agent.electionComplete) {

            return true;
        }

        return false;
    }


    /**
     * Agent converted (infected) another. Increment the conversions counter
     */
    public void converted() {
        conversions++;
    }


    /**
     * Agent met a follower. Increment the met followers counter
     */
    public void metFollower() {
        metFollowers++;
    }

//	----------		PUBLIC METHODS - GETTERS		----------


    public int getAID() {
        return AID;
    }


    public int getLeaderAID() {
        return leaderAID;
    }


    public int getConversions() {
        return conversions;
    }


    public int getMetFollowers() {
        return metFollowers;
    }


    public boolean isLeader() {
    	if (isElectionComplete())
    		this.isLeader = leaderAID == AID;
    	return isLeader;
    }


    public boolean isElectionComplete() {
        return electionComplete;
    }


// ----------		PUBLIC METHODS - SETTERS		----------


    public void setAID(int aID) {
        AID = aID;
    }


    public void setLeaderAID(int leaderAID) {
        this.leaderAID = leaderAID;
    }


    public void setConversions(int conversions) {
        this.conversions = conversions;
    }


    public void setMetFollowers(int metFollowers) {
        this.metFollowers = metFollowers;
    }


    public void setLeader(boolean isLeader) {
        this.isLeader = isLeader;
    }


    public void setElectionComplete(boolean electionComplete) {
        this.electionComplete = electionComplete;
    }
    
// -------------------------------------------------------------------


	public void disengage() {
	    this.busy = false;
	}

	public boolean isEngaged() {
	    return this.busy;
	}
	
	public void engage() {
	    this.busy = true;
	}

	public void updateLeader(int NewLeader) {
	    this.leaderAID = Math.max(NewLeader, this.leaderAID);
	}


// -------------------------------------------------------------------

// ------------------ Simulation Methods -----------------------------
	
	public Dimension getSize() {
	    return size;
	}

	public void setColor(Color color) {
	    this.color = color;
	}

	public void setLocation(Point location) {
	    this.location = location;
	}
	
	public void setInfoLocation(Point location) {
	    this.infoLocation = location;
	}
	
	public Color getColor() {
	    return color;
	}

	public Point getLocation() {
	    return location;
	}
	
	public Point getInfoLocation() {
	    return infoLocation;
	}
	
	protected void paint(Graphics2D g2d) {

	    Point p = getLocation();
	    Point q = getInfoLocation();
	    if (p != null) {
		g2d.setColor(getColor());
		Dimension size = getSize();
		g2d.fillOval(p.x-size.width/2, p.y-size.height/2, size.width, size.height);
		g2d.drawString(this.AID + "" ,q.x,q.y);
	    }

	}
	
	
}
