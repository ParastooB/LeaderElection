package backend;

import javax.swing.*;
import java.awt.*;

public class AgentsClient {

	public static final int FrameSizeX = 800;
	public static final int FrameSizeY = 800;

    public static void main(String[] args) {
//        new AgentsClient();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
        } catch (InstantiationException ex) {
        } catch (IllegalAccessException ex) {
        } catch (UnsupportedLookAndFeelException ex) {
        }

        JFrame frame = new JFrame("Spot");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        AgentsGroup agents = new AgentsGroup();
        frame.add(agents);
        frame.setSize(FrameSizeX, FrameSizeY);
        frame.setVisible(true);
        ThreadsGroup m =  new ThreadsGroup(agents);
        try {
        	Thread.sleep(1000);
        } catch (InterruptedException ex) {
        	System.out.println("didn't sleep for 1000 ms");
        }
        System.out.println("Starting . . . ");
        m.startElection();
        try {
        	Thread.sleep(0,1);
        } catch (InterruptedException ex) {
        	System.out.println("didn't sleep for 1 ms");
        }
        while (!agents.isElectionComplete()){
	        m.pauseElection();
	        try {
	        	Thread.sleep(1000);
	        } catch (InterruptedException ex) {
	        	System.out.println("didn't sleep for 1000 ms");
	        }
	        m.resumeElection();
	        try {
	        	Thread.sleep(0,1);
	        } catch (InterruptedException ex) {
	        	System.out.println("didn't sleep for 1 ms");
	        }
        }
        System.out.println("Done");
    }

    public AgentsClient() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
            }
        });
    }
}
