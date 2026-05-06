package a.entity.gus.x.swing.scrollpane.comptoscroll;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JScrollPane;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20140908";}
	
	public Object t(Object obj) throws Exception
	{
		JComponent comp = (JComponent) obj;
		return new JScrollPane(comp);
	}
}
