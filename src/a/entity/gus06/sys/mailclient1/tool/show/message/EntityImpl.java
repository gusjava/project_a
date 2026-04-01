package a.entity.gus06.sys.mailclient1.tool.show.message;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20240314";}


	private Service show;
	private Service newViewer;
	
	public EntityImpl() throws Exception
	{
		show = Outside.service(this,"gus06.swing.frame.show");
		newViewer = Outside.service(this,"factory#gus.sys.mailclient1.tool.show.message.viewer");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object viewer = newViewer.g();
		((P)viewer).p(obj);
		
		String title = "Message detail";
		show.v(title, viewer);
	}
}