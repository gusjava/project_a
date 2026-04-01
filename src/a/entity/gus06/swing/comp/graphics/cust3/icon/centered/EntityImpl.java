package a.entity.gus06.swing.comp.graphics.cust3.icon.centered;

import a.framework.*;
import java.awt.Graphics2D;
import javax.swing.Icon;
import javax.swing.JComponent;


public class EntityImpl implements Entity, P {

	public String creationDate() {return "20141206";}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] t = (Object[]) obj;
		if(t.length!=3) throw new Exception("Wrong data number: "+t.length);
		
		JComponent comp = (JComponent) t[0];
		Graphics2D g = (Graphics2D) t[1];
		Icon icon = (Icon) t[2];
		
		int x = comp.getWidth()/2-icon.getIconWidth()/2;
		int y = comp.getHeight()/2-icon.getIconHeight()/2;
		
		icon.paintIcon(comp,g,x,y);
	}
}
