package a.entity.gus.y.app1.starttime;

import java.text.SimpleDateFormat;
import java.util.Date;
import a.framework.*;

public class EntityImpl implements Entity, G {
	public String creationDate() {return "20140703";}

	private String timeStamp;

	public EntityImpl() throws Exception
	{
		Date date = (Date) Outside.resource(this,"launch.date");
		timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(date);
	}
	
	public Object g() throws Exception
	{return timeStamp;}
}
