package a.entity.gus06.swing.comp.graphics.cust3.icon.dnd1;

import a.framework.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import javax.swing.Icon;
import javax.swing.JComponent;


public class EntityImpl implements Entity, P {

	public String creationDate() {return "20141206";}
	
	public static final Font FONT = new Font("Courrier",Font.BOLD,24);
	public static final Color COLOR = new Color(52,100,188);
	public static final String MESSAGE = "Drop directories here";

	private Service setRenderingHints1;
	private Service setRenderingHints2;
	private Service drawIcon;
	private Icon icon;

	
	public EntityImpl() throws Exception
	{
		setRenderingHints1 = Outside.service(this,"gus06.graphics.setrenderinghint.high1");
		setRenderingHints2 = Outside.service(this,"gus06.graphics.setrenderinghint.default1");
		drawIcon = Outside.service(this,"gus06.swing.comp.graphics.cust3.icon.centered");
		icon = (Icon) Outside.resource(this,"icon#GUI_dnd");
	}



	public void p(Object obj) throws Exception
	{
		Object[] t = (Object[]) obj;
		if(t.length!=2) throw new Exception("Wrong data number: "+t.length);
		
		JComponent comp = (JComponent) t[0];
		Graphics2D g = (Graphics2D) t[1];
		
		Font font0 = g.getFont();
		g.setFont(FONT);
		setRenderingHints1.p(g);
		
		drawBox(comp,g);
		
		g.setFont(font0);
		setRenderingHints2.p(g);
		
		drawIcon.p(new Object[]{comp,g,icon});
	}
	
	
	
	private void drawBox(JComponent comp, Graphics2D g) throws Exception
	{
		FontMetrics metrics = g.getFontMetrics(FONT);
		
		int ws = metrics.stringWidth(MESSAGE);
		int hs = metrics.getHeight();
		int wc = comp.getWidth();
		int hc = comp.getHeight();
		int hi = icon.getIconHeight();
		
		g.setColor(Color.WHITE);
		g.fillRoundRect(
				wc/2 - ws/2 - 20,
				hc/2 - hi/2 - 20,
				ws + 40,
				hi + hs + 40,
				30,
				30);
		
		g.setColor(COLOR);
		g.drawString(MESSAGE,
				wc/2 - ws/2,
				hc/2 + hi/2 + hs);
	}
}

