package a.entity.gus06.app.init.mainframe.persister;

import a.framework.*;
import javax.swing.JFrame;


public class EntityImpl implements Entity {

	public String creationDate() {return "20140912";}

	public static final String PERSIST_KEY = "mainframe.bounds";

	private Service persistFrame;
	
	private JFrame frame;
	


	public EntityImpl() throws Exception
	{
		persistFrame = Outside.service(this,"gus06.swing.frame.persister.bounds");
		
		frame = (JFrame) Outside.resource(this,"mainframe");
		persistFrame.v(PERSIST_KEY,frame);
	}
}
