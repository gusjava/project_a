package a.entity.gus06.time.date.is.af.be0.month1;

import a.framework.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20201107";}


	private Service build;
	
	public EntityImpl() throws Exception
	{
		build = Outside.service(this,"gus06.time.date.build.be0.month1");
	}

	public boolean f(Object obj) throws Exception
	{
		Date date = (Date) obj;
		Date date0 = (Date) build.g();
		
		return date.after(date0);
	}
}