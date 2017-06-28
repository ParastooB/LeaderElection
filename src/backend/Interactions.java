package backend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


// TO DO: turn this whole class to Lists
public class Interactions {
	int[][][] interactions;
//	ArrayList<Integer> interactions = new ArrayList<Integer>();
	int agentCount;
	String errors = new String();
	int success = 0;
	int failure = 0;
	
    public Interactions(int Count) {
    	interactions = new int[Count][2][Count*10];
    	this.agentCount = Count;
    }
    
    public void initi(int i, int ID){
    	this.interactions[i][0][0] = ID;
		this.interactions[i][1][0] = ID;
    }
    
    private boolean hasID(int ID){
    	boolean result = false; 
    	int a[] = new int [agentCount];
    	for (int i = 0; i < agentCount; i++){
    		a[i] = interactions[i][0][0];
    		result = (a[i] == ID);
    		if (result == true)
    			break;
    	}
    	return result;
    }
    
    private int searchID(int ID){
    	boolean result = false; 
    	int location = -1;
    	for (int i = 0; i < agentCount; i++){
    		result = (this.interactions[i][0][0] == ID);
    		if (result == true){
    			location = i;
    			break;
    		}
    		else 
    			this.errors = this.errors + "ID doesn't exist\n";
    		System.out.println(location);
    	}
    	return location;
    }
    
    public void successful(int ID, int ID2){
    	int a = searchID(ID);
    	if (hasID(ID) && hasID(ID2)){
    		this.success ++;
	    	this.interactions[a][0][this.success] = ID2;
    	}
    	else
    		this.errors = this.errors + "ID doesn't exist\n";
    }

    public void failed(int ID, int ID2){
    	int a = searchID(ID);
    	if (hasID(ID) && hasID(ID2)){
    		this.failure ++;
			this.interactions[a][1][this.failure] = ID2;
    	}
    	else
    		this.errors = this.errors + "ID doesn't exist\n";
    }
    
    public int getIndex(int ID){
    	return searchID(ID);
    }
    
    public int getID(int i){
    	return this.interactions[i][0][0];
    }
    
    public ArrayList<Integer> getSuccessfulInteractions(int ID){
    	ArrayList<Integer> l = new ArrayList<Integer>();
    	int agntLoc = searchID(ID);
    	int i = 1;
    	while (this.interactions[agntLoc][0][i] != 0){
    		l.add(this.interactions[agntLoc][0][i]);
    		i ++;
    	}
    	return l;
    }
    
    public ArrayList<Integer> getFailedInteractions(int ID){
    	ArrayList<Integer> l = new ArrayList<Integer>();
    	int agntLoc = searchID(ID);
    	int i = 1;
    	while (this.interactions[agntLoc][1][i] != 0){
    		l.add(this.interactions[agntLoc][1][i]);
    		i ++;
    	}
    	return l;
    }

	@Override
	public String toString() {
		String result = new String();
		String result2 = new String();
		for (int i = 1; i <= agentCount; i++){
			result = result + "Successful Interactions [interactions=";
			result2 = result2 + "Failed Interactions [interactions=";
			for (int j = 0; j < 10*agentCount; j++){
				result = result + " " + interactions[i-1][0][j] + ", ";
				result2 = result2 + " " + interactions[i-1][1][j] + ", ";
			}
			result = result + "agent count = " + i + "]\n";
			result2 = result2 + "agent count = " + i + "]\n";
		}
		return result + result2;
	}
    
    

}
