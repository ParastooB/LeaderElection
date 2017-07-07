package backend;

import javax.swing.*;
import java.awt.*;

public class AgentsClient {

	public static final int FrameSizeX = 1050;
	public static final int FrameSizeY = 1000;

    public static void main(String[] args) {
        new AgentsClient();
    }

    public AgentsClient() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
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
                	Thread.sleep(100);
                } catch (InterruptedException ex) {
                	}
                m.pauseElection();
                try {
                	Thread.sleep(100);
                } catch (InterruptedException ex) {
                	}
                m.resumeElection();
            }
        });
    }
}
