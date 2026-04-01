package a.entity.gus06.app.mainframe.draggable;

import a.framework.*;
import javax.swing.JFrame;

public class EntityImpl implements Entity {

	public String creationDate() {return "20160505";}


	private Service dragframe;

	public EntityImpl() throws Exception
	{
		dragframe = Outside.service(this,"gus06.swing.comp.cust.dragframe");
		
		JFrame frame = (JFrame) Outside.resource(this,"mainframe");
		dragframe.p(frame);
	}
}
