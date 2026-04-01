package a.entity.gus06.app.mainframe.perform.hide;

import a.framework.*;
import javax.swing.JFrame;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20200318";}


	private JFrame frame;
	
	public EntityImpl() throws Exception
	{
		frame = (JFrame) Outside.resource(this,"mainframe");
	}
	
	public void e() throws Exception
	{
		frame.setVisible(false);
	}
}
