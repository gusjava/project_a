package a.entity.gus06.swing.comp.cust3.foreground.full;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150613";}


	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Component comp = (Component) o[0];
		Color color = (Color) o[1];
		
		handleForeground(comp,color);
	}
	
	
	private void handleForeground(Component comp, Color color)
	{
		comp.setForeground(color);
		if(comp instanceof Container)
		{
			Container c = (Container) comp;
			for(int i=0;i<c.getComponentCount();i++)
				handleForeground(c.getComponent(i),color);
		}
	}
}
