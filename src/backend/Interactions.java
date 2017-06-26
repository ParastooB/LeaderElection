package backend;

import java.util.ArrayList;

public class Interactions {
	int[][][] interactions;
	ArrayList interactions = new ArrayList();
	int agentCount;
	
    public Interactions(int Count) {
    	interactions = new int[Count][Count*10][2];
    	this.agentCount = Count;
    }
    
    public void initi(int i, int ID){
    	this.interactions[i][0][0] = ID;
		this.interactions[i][0][1] = ID;
    }
    
    public boolean hasID(int ID){
    	int a[] = new int [agentCount];
    	for (int i = 0; i < agentCount; i++){
    		a[i] = interactions
    	}
    }
    
    public int searchID(int ID){
    	for ()
    }
    
    public void successful(int i, int ID){
    	this.interactions[i][0][0] = ID;
		this.interactions[i][0][1] = ID;
    }
    Integer
    public void failed(int i, int ID){
    	this.interactions[i][0][0] = ID;
		this.interactions[i][0][1] = ID;
    }
    
    public void in(int i, int ID){
    	this.interactions[i][0][0] = ID;
		this.interactions[i][0][1] = ID;
    }
    
    public void i(int i, int ID){
    	this.interactions[i][0][0] = ID;
		this.interactions[i][0][1] = ID;
    }

}
