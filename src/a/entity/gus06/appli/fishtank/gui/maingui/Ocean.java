package a.entity.gus06.appli.fishtank.gui.maingui;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Random;

import javax.swing.JPanel;

public class Ocean extends Observable implements CONST {

	protected List<Fish> fishs;
	protected List<Rock> rocks;
	protected Random generator;
	protected JPanel panel;
	
	public Ocean(JPanel panel) {
		this.panel = panel;
		generator = new Random();
		rocks = new ArrayList<>();
		fishs = new ArrayList<>();
		
		for(int i=0;i<NB_FISH;i++) {
			double x = generator.nextDouble()*100;
			double y = generator.nextDouble()*100;
			double dir = generator.nextDouble()*2*Math.PI;
			
			fishs.add(new Fish(x,y,dir));
		}
	}
	
	
	
	
	
	
	
	
	protected void updateRocks() {
		if(generator.nextDouble()<ZONE_RATE) {
			double x = generator.nextDouble()*panel.getWidth();
			double y = generator.nextDouble()*panel.getHeight();
			
			addRock(x,y);
		}
		
		for(Rock rock : rocks) {
			rock.update();
		}
		rocks.removeIf(o -> o.isOver());
	}
	
	protected void updateFishs() {
		for(Fish fish : fishs) {
			fish.update(fishs, rocks, panel.getWidth(), panel.getHeight());
		}
	}
	

	
	public synchronized void addRock(double x, double y) {
		rocks.add(new Rock(x, y));
	}
	
	
	
	public synchronized void update() {
		updateRocks();
		updateFishs();
		setChanged();
		notifyObservers();
	}
	
	public synchronized void paint(Graphics g) {
		g.setColor(COLOR_FISH);
		for(Fish p : fishs) drawFish(p, g);
		
		g.setColor(COLOR_ROCK);
		for(Rock z : rocks) drawRock(z, g);
	}
	
	
	private void drawFish(Fish p, Graphics g) {
		g.drawLine((int) p.x , (int) p.y, (int) (p.x-10*p.vx), (int) (p.y-10*p.vy));
	}
	
	private void drawRock(Rock z, Graphics g) {
		g.fillOval((int) (z.x-z.getRadius()) , (int) (z.y-z.getRadius()), (int) (z.getRadius()*2), (int) (z.getRadius()*2));
	}
}
