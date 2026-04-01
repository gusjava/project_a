package a.entity.gus06.appli.vindinium.map.viewer.board.mousetoposition;

import java.awt.Insets;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170917";}

	public static final int GAP = 100;


	public Object t(Object obj) throws Exception
	{
		Object[] t = (Object[]) obj;
		if(t.length!=3) throw new Exception("Wrong data number: "+t.length);
		
		return pointToPos((int[][]) t[0], (JPanel) t[1], (MouseEvent) t[2]);
	}

	
	private int[] pointToPos(int[][] tiles, JPanel panel, MouseEvent e)
	{
		double img_size = tiles.length*GAP;
		
		double x_mouse = e.getX();
		double y_mouse = e.getY();
		
		Insets ins = panel.getInsets();
		
		double w_screen = (double) panel.getWidth()-ins.left-ins.right;
		double h_screen = (double) panel.getHeight()-ins.bottom-ins.top;

		double cx = w_screen / img_size;
		double cy = h_screen / img_size;
		
		if(cx>=cy)
		{
			double offset = (w_screen-img_size*cy)/2.0;
			double zoom =  img_size/h_screen;
			
			double x0 = ins.left + offset;
			double y0 = ins.top;
			
			double xm = (x_mouse - x0) * zoom;
			double ym = (y_mouse - y0) * zoom;
			
			int xr = (int) (ym/GAP);
			int yr = (int) (xm/GAP);
			
			if(xr<0 || xr>tiles.length-1) return null;
			if(yr<0 || yr>tiles.length-1) return null;
			
			return new int[] {xr,yr};
		}
		else
		{
			double offset = (h_screen-img_size*cx)/2.0;
			double zoom =  img_size/w_screen;
			
			double x0 = ins.left;
			double y0 = ins.top + offset;
			
			double xm = (x_mouse - x0) * zoom;
			double ym = (y_mouse - y0) * zoom;
			
			int xr = (int) (ym/GAP);
			int yr = (int) (xm/GAP);
			
			if(xr<0 || xr>tiles.length-1) return null;
			if(yr<0 || yr>tiles.length-1) return null;
			
			return new int[] {xr,yr};
		}
	}
}
