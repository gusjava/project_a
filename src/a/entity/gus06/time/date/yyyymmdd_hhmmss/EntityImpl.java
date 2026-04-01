package a.entity.gus06.time.date.yyyymmdd_hhmmss;

import java.text.SimpleDateFormat;
import java.util.Date;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160612";}


	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
	
	
	public Object t(Object obj) throws Exception
	{return sdf.format((Date) obj);}
}
