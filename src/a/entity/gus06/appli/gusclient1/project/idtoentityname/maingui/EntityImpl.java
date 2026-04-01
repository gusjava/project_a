package a.entity.gus06.appli.gusclient1.project.idtoentityname.maingui;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140905";}


	private Service idToAppliPart;


	public EntityImpl() throws Exception
	{
		idToAppliPart = Outside.service(this,"gus06.appli.gusclient1.project.idtoapplipart");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String part = (String) idToAppliPart.t(obj);
		return part+"gui.maingui";
	}
}
