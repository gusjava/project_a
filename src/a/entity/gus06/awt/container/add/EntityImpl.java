package a.entity.gus06.awt.container.add;

import a.framework.*;
import java.awt.Container;
import java.awt.Component;
import javax.swing.JMenu;
import javax.swing.Action;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20170520";}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Container container = (Container) o[0];
		Object item = o[1];
		
		if(container instanceof JMenu)
		{addToJMenu((JMenu) container, item);return;}
		
		container.add((Component) item);
	}
	
	
	private void addToJMenu(JMenu menu, Object item)throws Exception
	{
		if(item instanceof Action) {menu.add((Action) item);return;}
		if(item instanceof Component) {menu.add((Component) item);return;}
		
		throw new Exception("Invalid item type: "+item.getClass().getName());
	}
}
