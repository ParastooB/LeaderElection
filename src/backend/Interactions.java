package backend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


// TO DO: turn this whole class to Lists
public class Interactions {

	Map<Integer, Tuple<ArrayList<Integer>,ArrayList<Integer>>> m = new HashMap<Integer, Tuple<ArrayList<Integer>,ArrayList<Integer>>>();
	
	int agentCount;
	String errors = new String();
	int success = 0;
	int failure = 0;
	
    public Interactions(int Count) {
    	this.agentCount = Count;
    }
    
    public void initi(int ID){
    	ArrayList<Integer> failed = new ArrayList<Integer>();
    	ArrayList<Integer> succeed = new ArrayList<Integer>();
    	Tuple<ArrayList<Integer>,ArrayList<Integer>> tup = new Tuple<ArrayList<Integer>,ArrayList<Integer>>(succeed, failed);
    	this.m.put(ID, tup);
    }
    
    private boolean hasID(int ID){
    	boolean result = this.m.containsKey(ID);
    	return result;
    }
    
    public synchronized void successful(int ID, int ID2){
    	if (hasID(ID) && hasID(ID2)){
    		this.success ++;
	    	boolean SError = this.m.get(ID).x.add(ID2);
    	}
    	else
    		this.errors = this.errors + "ID doesn't exist\n";
    }

    public synchronized void failed(int ID, int ID2){
    	if (hasID(ID) && hasID(ID2)){
    		this.failure ++;
    		boolean FError = this.m.get(ID).y.add(ID2);
    	}
    	else
    		this.errors = this.errors + "ID doesn't exist\n";
    }
    
    public ArrayList<Integer> getSuccessfulInteractions(int ID){
    	ArrayList<Integer> l = this.m.get(ID).x;
    	return l;
    }
    
    public ArrayList<Integer> getFailedInteractions(int ID){
    	ArrayList<Integer> l = this.m.get(ID).y;
    	return l;
    }

	@Override
	public String toString() {
		String result = new String();
		String result2 = new String();
		for (Integer p : this.m.keySet()){
			result = result + "Successful Interactions of Agent " + p + " --> " +
						this.m.get(p).x + "\n";
			result2 = result2 + "Failed Interactions of Agent " + p + " --> " +
						this.m.get(p).y + "\n";

		}
		return result + result2;
	}
}
