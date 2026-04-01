package a.entity.gus06.swing.desktoppane.buildpane1;

import a.framework.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JDesktopPane;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20191120";}


	private Service custImage;

	public EntityImpl() throws Exception
	{
		custImage = Outside.service(this,"gus06.swing.comp.graphics.cust3.image");
	}


	public Object i() throws Exception
	{return new JDesktopPane1();}

	
	private class JDesktopPane1 extends JDesktopPane implements P, V
	{  
		private String mode;
		private Object image = null;
		private boolean valid = true;
		
		public void paintComponent(Graphics g)
		{  
			super.paintComponent(g);
			if(valid && image!=null)
			valid = paintImage(this,(Graphics2D)g,image,mode);
		}
		
		public void p(Object obj) throws Exception
		{
			mode = null;
			image = obj;
			valid = true;
			repaint();
		}
		
		public void v(String key, Object obj) throws Exception
		{
			mode = key;
			image = obj;
			valid = true;
			repaint();
		}
	}
	
	
	private boolean paintImage(JDesktopPane comp, Graphics2D g2, Object image, String mode)
	{
		try
		{
			custImage.p(new Object[]{comp,g2,image,mode});
			return true;
		}
		catch(Exception e)
		{Outside.err(this,"paintImage(JDesktopPane,Graphics2D,Object,String)",e);}
		return false;
	}
}
