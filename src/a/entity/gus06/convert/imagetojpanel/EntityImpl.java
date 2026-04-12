package a.entity.gus06.convert.imagetojpanel;

import a.framework.*;
import javax.swing.JPanel;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180401";}


	private Service screenBuilder;
	
	public EntityImpl() throws Exception
	{
		screenBuilder = Outside.service(this,"factory#gus06.swing.panel.screen.image");
	}


	
	public Object t(Object obj) throws Exception
	{
		Object screen = screenBuilder.g();
		((P) screen).p(obj);
		return (JPanel) ((I) screen).i();
	}
}
