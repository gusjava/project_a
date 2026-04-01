package a.entity.gus06.app.outside.lastmodified.timestamp;

import a.framework.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20170411";}

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");


	private Service find;

	public EntityImpl() throws Exception
	{
		find = Outside.service(this,"gus06.app.outside.lastmodified");
	}
	
	public Object g() throws Exception
	{
		Date date = (Date) find.g();
		return sdf.format(date);
	}
}
