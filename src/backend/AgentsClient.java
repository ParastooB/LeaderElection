package backend;

import javax.swing.*;
import java.awt.*;

public class AgentsClient {

	public static final int FrameSizeX = 1050;
	public static final int FrameSizeY = 1000;

    public static void main(String[] args) {
        //new AgentsClient();
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
        Agent random = agents.leader();
        try {
        	Thread.sleep(1000);
        } catch (InterruptedException ex) {
        	System.out.println("didn't sleep for 100 ms");
        }
        System.out.println("Start");
        m.startOne(random);
/*        try {
        	Thread.sleep(1000);
        } catch (InterruptedException ex) {
        	System.out.println("didn't sleep for 100 ms");
        }*/
        try {
			Thread.currentThread().wait();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        m.resumeOne(random);
        try {
        	m.waitForOne(random);
        } catch (InterruptedException e) {
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
