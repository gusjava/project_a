package a.entity.gus06.sys.gameengine1.control.mouse.screentoimage;

import java.awt.Insets;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200515";}


	private Service initSize;
	
	private double w_img;
	private double h_img;
	
	

	public EntityImpl() throws Exception
	{
		initSize = Outside.service(this,"gus06.sys.gameengine1.producer.initsize");
		
		int[] p = (int[]) initSize.g();
		w_img = (double) p[0];
		h_img = (double) p[1];
	}


	

	public Object t(Object obj) throws Exception
	{
		Object[] t = (Object[]) obj;
		
		MouseEvent evt = (MouseEvent) t[0];
		JComponent comp = (JComponent) t[1];
		Insets ins = comp.getInsets();
		
		
		double x_mouse = (double) evt.getX();
		double y_mouse = (double) evt.getY();
		
		double w_screen = (double) comp.getWidth()-ins.left-ins.right;
		double h_screen = (double) comp.getHeight()-ins.bottom-ins.top;
		
		double cx = w_screen / w_img;
		double cy = h_screen / h_img;
		

		if(cx>=cy)
		{
			double offset = (w_screen-w_img*cy)/2.0;
			double zoom =  h_img/h_screen;
			
			double x0 = ins.left + offset;
			double y0 = ins.top;
			
			double xm = (x_mouse - x0) * zoom;
			double ym = (y_mouse - y0) * zoom;
			
			return new int[] {(int) xm,(int) ym};
		}
		else
		{
			double offset = (h_screen-h_img*cx)/2.0;
			double zoom =  w_img/w_screen;
			
			double x0 = ins.left;
			double y0 = ins.top + offset;
			
			double xm = (x_mouse - x0) * zoom;
			double ym = (y_mouse - y0) * zoom;
			
			return new int[] {(int) xm,(int) ym};
		}
	}


}
