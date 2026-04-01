package a.entity.gus06.string.transform.format.timestamp.yyyymm;

import a.framework.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150920";}


	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
	
	private Service stringToDate;


	public EntityImpl() throws Exception
	{stringToDate = Outside.service(this,"gus06.convert.stringtodate");}
	
	
	public Object t(Object obj) throws Exception
	{
		Date d = (Date) stringToDate.t(obj);
		return sdf.format(d);
	}
}
