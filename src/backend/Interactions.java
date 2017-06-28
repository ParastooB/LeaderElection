package backend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Interactions {
	int[][][] interactions;
//	ArrayList<Integer> interactions = new ArrayList<Integer>();
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
    	boolean result = false; 
    	int location = -1;
    	int a[] = new int [agentCount];
    	for (int i = 0; i < agentCount; i++){
    		a[i] = interactions[i][0][0];
    		result = result || (a[i] == ID);
    		if (result == true)
    			location = i;
    	}
    	return result;
    }
    
    public int searchID(int ID){
    	boolean result = false; 
    	int location = -1;
    	int a[] = new int [agentCount];
    	for (int i = 0; i < agentCount; i++){
    		a[i] = interactions[i][0][0];
    		result = result || (a[i] == ID);
    		if (result == true)
    			location = i;
    	}
    	return location;
    }
    
    public void successful(int i, int ID){
    	int a = this.interactions[i].length;
    	this.interactions[i][a+1][0] = ID;
    }

    public void failed(int i, int ID){
    	int a = this.interactions[i].length;
		this.interactions[i][a+1][1] = ID;
    }
    
    public void in(int i, int ID){
    	this.interactions[i][0][0] = ID;
		this.interactions[i][0][1] = ID;
    }
    
    public void i(int i, int ID){
    	this.interactions[i][0][0] = ID;
		this.interactions[i][0][1] = ID;
    }

	@Override
	public String toString() {
		String result = new String();
		for (int i = 0; i < agentCount; i++){
			for (int j = 0; j < 2*agentCount; j++){
				result = result + "Interactions [interactions=" + interactions[i][j] ;
			}
			result = result + ", agent= " + i+1 + "]\n";
		}
		return result;
	}
    
    

}
