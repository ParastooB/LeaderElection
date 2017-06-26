package backend;

import java.util.ArrayList;
import java.util.Collections;

public class UniqueRandomNumbers {
	
    public static ArrayList<Integer> UniqueRandomNumber (int a, int b, int s){
    	ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i=1; i<11; i++) {
			list.add(new Integer(i));
		}
	    Collections.shuffle(list);
	    return (ArrayList<Integer>) list.subList(0,s-1);
    }
    
    public static int maxList (ArrayList<Integer> list){
    	return Collections.max(list);
    }
    
	public static int random(int maxRange) {
        return (int) Math.round((Math.random() * maxRange));
    }
}