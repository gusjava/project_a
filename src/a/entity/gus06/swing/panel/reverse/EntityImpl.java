package a.entity.gus06.swing.panel.reverse;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.Component;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20161126";}

	
	
	public Object t(Object obj) throws Exception
	{
		p(obj);
		return obj;
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		JPanel panel = (JPanel) obj;
		
		int n = panel.getComponentCount();
		List l = new ArrayList();
		
		for(int i=0;i<n;i++)
		l.add(panel.getComponent(i));
		
		panel.removeAll();
		
		for(int i=0;i<n;i++)
		panel.add((Component) l.get(n-i-1));
	}
}
