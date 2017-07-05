package backend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UniqueRandomNumbers {
	private static ArrayList<Integer> list = new ArrayList<Integer>();
	private static ArrayList<Integer> result = new ArrayList<Integer>();
	
    public static ArrayList<Integer> UniqueRandomNumber (int a, int b, int s){
		for (int i=a; i<b; i++) {
			list.add(new Integer(i));
		}
	    Collections.shuffle(list);
	    List<Integer> r = list.subList(0,s-1);
	    result.addAll(r);
	    return result;
	    
    }
    
	public static int random(int maxRange) {
        return (int) Math.round((Math.random() * maxRange));
    }
}