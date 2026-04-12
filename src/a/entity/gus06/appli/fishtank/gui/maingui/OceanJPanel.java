package a.entity.gus06.appli.fishtank.gui.maingui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

public class OceanJPanel extends JPanel implements Observer, MouseListener, CONST {
	
	protected Ocean ocean;
	protected Timer timer;
	
	public OceanJPanel() {
		setBackground(COLOR_OCEAN);
		addMouseListener(this);
		setFocusable(true);
	}
	
	public void start() {
		ocean = new Ocean(this);
		ocean.addObserver(this);
		
		TimerTask task = new TimerTask() {
			public void run() {
				ocean.update();
			}
		};
		
		timer = new Timer();
		timer.scheduleAtFixedRate(task, 0, RATE);
	}
	
	
	
	
	public void update(Observable o, Object arg) {
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		g.setColor(COLOR_OCEAN);
		super.paintComponent(g);
		ocean.paint(g);
	}

	public void mousePressed(MouseEvent e) {
		ocean.addRock(e.getX(), e.getY());
	}

	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
}
