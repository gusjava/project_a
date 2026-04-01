package a.entity.gus06.appli.gusdbmanager.gui.cx.data;

import a.framework.*;
import javax.swing.JPanel;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150613";}


	public EntityImpl() throws Exception
	{
	}
	
	
	public Object t(Object obj) throws Exception
	{
		return new JPanel();
	}
}
