package a.entity.gus06.swing.icon.build.sensitive;

import a.framework.*;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.Icon;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141203";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Icon icon_main = (Icon) o[0];
		Icon icon_rollover = buildIcon(o[1],icon_main);
		Icon icon_pressed = buildIcon(o[2],icon_main);
		
		return new SensitiveIcon(icon_main,icon_rollover,icon_pressed);
	}

	
	
	private Icon buildIcon(Object source, Icon icon) throws Exception
	{
		if(source==null)		return null;
		if(source instanceof Icon)	return (Icon) source;
		if(source instanceof T)		return (Icon) ((T) source).t(icon);
		if(source instanceof G)		return (Icon) ((G) source).g();
		
		throw new Exception("Invalid type: "+source.getClass().getName());
	}

	
	
	
	
	private class SensitiveIcon implements Icon, T
	{
		private Icon icon_main;
		private Icon icon_rollover;
		private Icon icon_pressed;
		
		private Map map;
		
		public SensitiveIcon(Icon icon_main, Icon icon_rollover, Icon icon_pressed)
		{
			this.icon_main = icon_main;
			this.icon_rollover = icon_rollover;
			this.icon_pressed = icon_pressed;
			
			map = new HashMap();
		}
		
		public void paintIcon(Component c, Graphics g, int x, int y)
		{
			IconPainter painter = getPainter(c);
			painter.updateInfos(x,y);
			
			if(painter.isPressed())		paintIcon(icon_pressed,c,g,x,y);
			else if(painter.isInside())	paintIcon(icon_rollover,c,g,x,y);
			else 				paintIcon(icon_main,c,g,x,y);
		}
		
		public int getIconWidth() {return getIconWidth(icon_main);}
		public int getIconHeight() {return getIconHeight(icon_main);}

		
		public Object t(Object obj) throws Exception
		{return getPainter((Component) obj);}
		
		
		
		
		private void paintIcon(Icon icon, Component c, Graphics g, int x, int y)
		{if(icon!=null) icon.paintIcon(c,g,x,y);}
		
		private int getIconWidth(Icon icon)
		{return icon!=null ? icon.getIconWidth() : 0;}
		
		private int getIconHeight(Icon icon)
		{return icon!=null ? icon.getIconHeight() : 0;}

		
		private IconPainter getPainter(Component c)
		{
			if(!map.containsKey(c))
				map.put(c,new IconPainter(this,c));
			return (IconPainter) map.get(c);
		}
	}
	
	
	
	
	
	
	private class IconPainter extends S1 implements MouseListener, MouseMotionListener
	{
		private Icon icon;
		private Component c;
		
		private boolean mouseInside = false;
		private boolean mousePressed = false;
		
		public boolean isInside() {return mouseInside;}
		public boolean isPressed() {return mouseInside && mousePressed;}
		
		private int x = 0;
		private int y = 0;
		
		public IconPainter(Icon icon, Component c)
		{
			this.icon = icon;
			this.c = c;
			
			c.addMouseListener(this);
			c.addMouseMotionListener(this);
		}
		
		public void updateInfos(int x, int y)
		{
			this.x = x;
			this.y = y;
		}
		
		private void refreshComponent()
		{c.repaint();}
		
		
		private int w(){return icon!=null ? icon.getIconWidth() : 0;}
		private int h(){return icon!=null ? icon.getIconHeight() : 0;}
		
		private Rectangle getBounds()
		{return new Rectangle(x,y,w(),h());}
		
		private boolean inside(MouseEvent e)
		{return getBounds().contains(e.getPoint());}
		
		public void mouseDragged(MouseEvent e)
		{
			mouseInside = false;
			mousePressed = false;
			refreshComponent();
		}
		
		public void mousePressed(MouseEvent e)
		{
			mousePressed = true;
			refreshComponent();
		}
		
		public void mouseClicked(MouseEvent e)
		{if(inside(e)) clicked();}
		
		public void mouseReleased(MouseEvent e)
		{mouseMoved(e);}
		
		public void mouseMoved(MouseEvent e)
		{
			boolean inside_ = mouseInside;
			mouseInside = inside(e);
			
			if(mousePressed)
			{
				mousePressed = false;
				refreshComponent();
			}
			if(!inside_ && mouseInside)
			{
				refreshComponent();
				entered();
			}
			else if(inside_ && !mouseInside)
			{
				refreshComponent();
				exited();
			}
		}
		
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e)
		{
			if(!mouseInside) return;
			mouseInside = false;
			refreshComponent();
			exited();
		}
		
		private void clicked()
		{send(this,"clicked()");}
		
		private void entered()
		{send(this,"entered()");}
		
		private void exited()
		{send(this,"exited()");}
	}

}