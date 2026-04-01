package a.entity.gus06.appli.vindinium.gui.tool.drawpath;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20170917";}

	public static final int GAP = 100;
	public static final int ARROW_THICKNESS = 4;
	

	private Service drawArrow;
	
	public EntityImpl() throws Exception
	{drawArrow = Outside.service(this,"gus06.graphics.draw.point.arrow2");}

	
	public void p(Object obj) throws Exception
	{
		Object[] t = (Object[]) obj;
		if(t.length!=2) throw new Exception("Wrong data number: "+t.length);
		
		Graphics2D g = (Graphics2D) t[0];
		int[][] path = (int[][]) t[1];
		if(path!=null && path.length>1) drawPath(g,path);
	}
	
	
	private void drawPath(Graphics2D g, int[][] path) throws Exception
	{
		g.setColor(Color.BLACK);
		g.setStroke(new BasicStroke(ARROW_THICKNESS));
		drawArrow.v("graphics2D",g);
		
		for(int i=0;i<path.length-1;i++)
		{
			int[] p1 = path[i];
			int[] p2 = path[i+1];
			
			int x1 = p1[0]*GAP + (int)(GAP/2.0);
			int y1 = p1[1]*GAP + (int)(GAP/2.0);
			
			int x2 = p2[0]*GAP + (int)(GAP/2.0);
			int y2 = p2[1]*GAP + (int)(GAP/2.0);
			
			drawArrow.p(new Point[]{new Point(y1,x1),new Point(y2,x2)});
		}
	}
}
