package a.entity.gus06.appli.demoswing.gui.builder;

import a.framework.*;
import javax.swing.JPanel;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141109";}


	public EntityImpl() throws Exception
	{
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String id = (String) obj;
		return new JPanel();
	}
}
