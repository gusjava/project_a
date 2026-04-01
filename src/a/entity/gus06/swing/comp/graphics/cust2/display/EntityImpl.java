package a.entity.gus06.swing.comp.graphics.cust2.display;

import a.framework.*;
import javax.swing.*;
import java.awt.*;


public class EntityImpl implements Entity, V {

	public String creationDate() {return "20140913";}


	private Service iconProvider;
	private Service setRenderingHints;

	public EntityImpl() throws Exception
	{
		iconProvider = Outside.service(this,"gus06.icon.provider");
		setRenderingHints = Outside.service(this,"gus06.graphics.setrenderinghint.high1");
	}

	
	private Icon icon(String id) throws Exception
	{return (Icon) iconProvider.t(id);}
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Graphics g = (Graphics) o[0];
		JComponent c = (JComponent) o[1];
		
		setRenderingHints.p(g);
		draw(g,c,key);
	}
	
	
	
	private void draw(Graphics g, JComponent c, String display) throws Exception
	{
		if(display==null || display.equals(""))
		{
			draw(g,c,null,"");
		}
		else if(!display.contains("#"))
		{
			draw(g,c,null,display);
		}
		else
		{
			String[] m = display.split("#",2);
			draw(g,c,icon(m[0]),m[1]);
		}
	}
	
	
	
	
	private void draw(Graphics g, JComponent c, Icon icon, String text)
	{
		g.setColor(c.getBackground());
		g.fillRect(0,0,c.getWidth(),c.getHeight());
		
		if(icon!=null)
		{
			int offset = icon.getIconWidth();
			drawIcon(g,c,icon);
			drawText(g,c,text,offset);
		}
		else drawText(g,c,text,0);
	}
	
	
	
	
	
	private void drawIcon(Graphics g, JComponent c, Icon icon)
	{
		int ih = icon.getIconHeight();
		int iw = icon.getIconWidth();
		int ix = 2;
		int iy = (c.getHeight()-ih)/2;
		icon.paintIcon(c,g,ix,iy);
	}
	
	
	
	
	private void drawText(Graphics g, JComponent c, String text, int offset)
	{
		FontMetrics metrics = c.getFontMetrics(c.getFont());
		int th = metrics.getHeight();
		
		g.setColor(c.getForeground());
		g.drawString(text,offset+2,th) ;
	}
}
