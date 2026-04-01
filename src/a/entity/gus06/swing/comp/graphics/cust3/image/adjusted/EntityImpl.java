package a.entity.gus06.swing.comp.graphics.cust3.image.adjusted;

import a.framework.*;
import java.awt.Graphics2D;
import javax.swing.Icon;
import javax.swing.JComponent;
import java.awt.image.RenderedImage;
import java.awt.Image;
import java.awt.Insets;
import java.awt.geom.AffineTransform;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20191120";}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] t = (Object[]) obj;
		if(t.length!=3) throw new Exception("Wrong data number: "+t.length);
		
		JComponent comp = (JComponent) t[0];
		Graphics2D g2 = (Graphics2D) t[1];
		Object image = t[2];
		
		if(image instanceof RenderedImage)
			paintRenderedImage(comp,g2,(RenderedImage) image);
		else if(image instanceof Image)
			paintImage(comp,g2,(Image) image);
		else throw new Exception("Invalid image type: "+image.getClass().getName());
	}
	
	
	
	
	private void paintRenderedImage(JComponent comp, Graphics2D g2, RenderedImage image)
	{
		int w = image.getWidth();
		int h = image.getHeight();
		if(w<=0 || h<=0) return;
		
		Insets ins = comp.getInsets();
		
		double cx = (double)(comp.getWidth()-ins.left-ins.right)/(double)w;
    		double cy = (double)(comp.getHeight()-ins.bottom-ins.top)/(double)h;
    		
    		if(cx>=cy)
    		{
    			int a = (int)((comp.getWidth()-ins.left-ins.right-w*cy)/2);
    			AffineTransform af = AffineTransform.getTranslateInstance(a+ins.left,ins.top);
			af.scale(cy,cy);
			g2.drawRenderedImage(image,af);
    		}
    		else
    		{
    			int a = (int)((comp.getHeight()-ins.bottom-ins.top-h*cx)/2);		
    			AffineTransform af = AffineTransform.getTranslateInstance(ins.left,a+ins.top);
    			af.scale(cx,cx);
			g2.drawRenderedImage(image,af);
		}
	}
	
	private void paintImage(JComponent comp, Graphics2D g2, Image image)
	{
		int w = image.getWidth(null);
		int h = image.getHeight(null);
		if(w<=0 || h<=0) return;
		
		Insets ins = comp.getInsets();
		
		double cx = (double)(comp.getWidth()-ins.left-ins.right)/(double)w;
    		double cy = (double)(comp.getHeight()-ins.bottom-ins.top)/(double)h;

		if(cx>=cy)
		{
			int a = (int)((comp.getWidth()-ins.left-ins.right-w*cy)/2);
			int dx = (int)(w*cy);
			int dy = comp.getHeight()-ins.bottom-ins.top;
			g2.drawImage(image,a+ins.left,ins.top,dx,dy,comp);
		}
		else
		{
			int a = (int)((comp.getHeight()-ins.bottom-ins.top-h*cx)/2);	
			int dx = comp.getWidth()-ins.left-ins.right;
			int dy = (int)(h*cx);
			g2.drawImage(image,ins.left,a+ins.top,dx,dy,comp);
		}
	}
}
