package a.entity.gus06.appli.gamelaser1.gui.settings;

import a.framework.*;
import javax.swing.JPanel;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20160123";}


	private JPanel panel;

	public EntityImpl() throws Exception
	{
		panel = new JPanel();
	}
	
	
	public Object i() throws Exception
	{
		return panel;
	}

}
