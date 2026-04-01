package a.entity.gus06.time.date.yyyymmdd;

import java.text.SimpleDateFormat;
import java.util.Date;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160612";}


	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	
	
	public Object t(Object obj) throws Exception
	{return sdf.format((Date) obj);}
}
