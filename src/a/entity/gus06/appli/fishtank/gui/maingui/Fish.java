package a.entity.gus06.appli.fishtank.gui.maingui;

import java.util.List;

public class Fish extends Element implements CONST {
	
	public double vx;
	public double vy;
	
	public Fish(double x, double y, double dir) {
		super(x, y);
		vx = Math.cos(dir);
		vy = Math.sin(dir);
	}

	public void updatePosition() {
		x += PAS * vx;
		y += PAS * vy;
	}
	
	protected boolean alignedTo(Fish p) {
		double d2 = distance2(p);
		return d2>D_MIN2 && d2<D_MAX2;
	}
	
	
	protected void normalize() {
		double l = Math.sqrt(vx*vx + vy*vy);
		vx /= l;
		vy /= l;
	}
	
	
	protected boolean avoidWalls(double xmin, double ymin, double xmax, double ymax) {
		
		if(x>xmin+D_MIN && x<xmax-D_MIN && y>ymin+D_MIN && y<ymax-D_MIN) return false;
		
		if(x<xmin) x = xmin;
		else if(x>xmax) x = xmax;
		
		if(x<xmin+D_MIN) vx += ACC_MUR;
		else if(x>xmax-D_MIN) vx -= ACC_MUR;
		
		if(y<ymin) y = ymin;
		else if(y>ymax) y = ymax;
		
		if(y<ymin+D_MIN) vy += ACC_MUR;
		else if(y>ymax-D_MIN) vy -= ACC_MUR;
		
		return true;
	}
	
	
	protected boolean avoidRocks(List<Rock> list) {
		Rock nearest = null;
		double d2Nearest = Double.MAX_VALUE;
		
		for(Rock rock : list) {
			double d2 = distance2(rock);
			if(d2<d2Nearest) {
				nearest = rock;
				d2Nearest = d2;
			}
		}
		
		if(nearest==null) return false;
		double dNearest = Math.sqrt(d2Nearest);
		if(dNearest > nearest.getRadius()) return false;
		
		double dx = (nearest.x-x) / dNearest;
		double dy = (nearest.y-y) / dNearest;
		
		vx -= dx/2;
		vy -= dy/2;
		normalize();
		
		return true;
	}
	
	
	protected boolean avoidFishs(List<Fish> list) {
		Fish nearest = null;
		double d2Nearest = Double.MAX_VALUE;
		
		for(Fish fish : list) if(fish!=this) {
			double d2 = distance2(fish);
			if(d2<d2Nearest) {
				nearest = fish;
				d2Nearest = d2;
			}
		}
		
		if(nearest==null) return false;
		double dNearest = Math.sqrt(d2Nearest);
		if(dNearest > D_MIN) return false;
		
		double dx = (nearest.x-x)/dNearest;
		double dy = (nearest.y-y)/dNearest;
		
		vx -= dx/4;
		vy -= dy/4;
		normalize();
		
		return true;
	}
	
	
	protected boolean computeAverageDirection(List<Fish> list) {
		double vx_total = 0;
		double vy_total = 0;
		int nbTotal = 0;
		
		for(Fish fish : list) if(fish!=this) {
			if(alignedTo(fish)) {
				vx_total += fish.vx;
				vy_total += fish.vy;
				nbTotal++;
			}
		}
		if(nbTotal==0) return false;
		
		double vx_align = vx_total/nbTotal;
		double vy_align = vy_total/nbTotal;
		
		vx = (vx+vx_align)/2;
		vy = (vy+vy_align)/2;
		normalize();
		
		return true;
	}
	
	
	public void update(List<Fish> fishs, List<Rock> rocks, double width, double height) {
		
		if(avoidWalls(0,0,width,height)) {
			updatePosition();
			return;
		}
		if(avoidRocks(rocks)) {
			updatePosition();
			return;
		}
		if(avoidFishs(fishs)) {
			updatePosition();
			return;
		}
		
		computeAverageDirection(fishs);
		updatePosition();
	}
}
