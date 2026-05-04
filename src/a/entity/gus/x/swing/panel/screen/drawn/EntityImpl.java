package a.entity.gus.x.swing.panel.screen.drawn;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.Graphics;

public class EntityImpl implements Entity, P, I {
	public String creationDate() {return "20161203";}

	private JPanel0 panel;
	private P drawer;
	private boolean errorOccured = false;

	public EntityImpl() throws Exception
	{panel = new JPanel0();}

	public void p(Object obj) throws Exception
	{drawer = (P) obj;}

	public Object i() throws Exception
	{return panel;}
	
	private void draw(Graphics g)
	{
		try
		{
			if(drawer==null) return;
			drawer.p(new Object[]{g,panel});
		}
		catch(Exception e)
		{
			Outside.err(this,"draw(Graphics)",e);
			errorOccured = true;
		}
	}
	
	private class JPanel0 extends JPanel
	{
		public void paintComponent(Graphics g)
		{if(!errorOccured) draw(g);}
	}
}
