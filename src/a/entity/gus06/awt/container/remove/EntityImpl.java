package a.entity.gus06.awt.container.remove;

import a.framework.*;
import java.awt.Container;
import java.awt.Component;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20170520";}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Container container = (Container) o[0];
		Component comp = (Component) o[1];
		
		container.remove(comp);
		container.repaint();
	}
}
