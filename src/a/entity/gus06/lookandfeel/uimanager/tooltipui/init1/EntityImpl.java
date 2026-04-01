package a.entity.gus06.lookandfeel.uimanager.tooltipui.init1;

import a.framework.*;
import javax.swing.plaf.metal.MetalToolTipUI;
import javax.swing.plaf.ComponentUI;
import javax.swing.*;
import java.awt.*;

public class EntityImpl implements Entity {

	public String creationDate() {return "20140913";}
	
	// A REVOIR ....


	private static ComponentUI ui;
	public static ComponentUI createUI(JComponent c) {return ui;}
	
	

	private Service paint;
	private Service findDim;


	public EntityImpl() throws Exception
	{
		paint = Outside.service(this,"gus06.swing.comp.graphics.cust2.display");
		findDim = Outside.service(this,"gus06.swing.comp.graphics.cust2.display.finddim");
		
		ui = new IconToolTipUI();
		UIManager.put("ToolTipUI",getClass().getName());
	}
	
	
	
	
	
	public class IconToolTipUI extends MetalToolTipUI
	{
		public void paint(Graphics g, JComponent c)
		{
			c.setBackground(Color.WHITE);
			super.paint(g,c);
		}
	}
	
	
	
	
//	public class IconToolTipUI extends MetalToolTipUI
//	{
//		public void paint(Graphics g, JComponent c)
//		{
//			String display = ((JToolTip)c).getTipText();
//			c.setBackground(Color.WHITE);
//			handlePaint(g,c,display);
//		}
//		public Dimension getPreferredSize(JComponent c)
//		{
//			String display = ((JToolTip)c).getTipText();
//			return findDim(display,c);
//		}
//	}
	
	
	
	private void handlePaint(Graphics g, JComponent c, String display)
	{
		try{paint.v(display,new Object[]{g,c});}
		catch(Exception e)
		{Outside.err(this,"handlePaint(Graphics,JComponent,String)",e);}
	}
	
	private Dimension findDim(String display, JComponent c)
	{
		try{return (Dimension) findDim.t(new Object[]{display,c});}
		catch(Exception e)
		{Outside.err(this,"displayToDim(String,JComponent)",e);}
		return new Dimension(0,0);
	}
}
