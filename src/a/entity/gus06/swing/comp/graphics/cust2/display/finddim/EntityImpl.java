package a.entity.gus06.swing.comp.graphics.cust2.display.finddim;

import a.framework.*;
import javax.swing.JComponent;
import java.awt.FontMetrics;
import java.awt.Dimension;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140913";}

	public static final int EDGE = 4;
	public static final int ICONGAP = 16;
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String display = (String) o[0];
		JComponent c = (JComponent) o[1];
		
		return displayToDim(display,c);
	}
	
	
	
	
	private Dimension displayToDim(String display, JComponent c)
	{
		if(display==null || display.equals("")) return new Dimension(0,0);
		if(!display.contains("#")) return textToDim(display,c);
		
		String text = display.split("#",2)[1];
		Dimension d = textToDim(text,c);
		return new Dimension(d.width+ICONGAP,d.height);
	}
	
	
	private Dimension textToDim(String text, JComponent c)
	{
		FontMetrics fm = c.getFontMetrics(c.getFont());
		if(fm==null) return new Dimension(0,0);
		
		int w = fm.stringWidth(text);
		int h = fm.getHeight();
		return new Dimension(w+EDGE*2,h+EDGE*2);
	}
}
