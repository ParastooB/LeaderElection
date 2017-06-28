package backend;

import java.util.ArrayList;

public class Dummy {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Interactions a = new Interactions(7);
		a.initi(0, 15);
		a.initi(2, 18);
		a.initi(3, 14);
		a.initi(4, 7);
		a.initi(5, 11);
		a.initi(6, 2);
		a.initi(1, 5);
		a.successful(15, 5);
		a.successful(15, 11);
		a.failed(15, 4);
		a.failed(15, 7);
		a.failed(15, 2);
		a.failed(15, 18);
		System.out.println(a);
		ArrayList<Integer> b = a.getFailedInteractions(15);
		System.out.println(b);
		b = a.getSuccessfulInteractions(15);
		System.out.println(b);

	}

}
