package a.entity.gus06.swing.comp.cust.opaque.full;

import java.awt.Component;
import java.awt.Container;
import a.framework.*;
import javax.swing.JComponent;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20191122";}


	public void p(Object obj) throws Exception
	{
		Component comp = (Component) obj;
		setOpaque(comp);
	}
	
	
	private void setOpaque(Component comp)
	{
		if(comp instanceof JComponent)
			((JComponent)comp).setOpaque(true);
		if(comp instanceof Container)
		{
			Container c = (Container) comp;
			for(int i=0;i<c.getComponentCount();i++)
				setOpaque(c.getComponent(i));
		}
	}
}
