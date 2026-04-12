package a.entity.gus.x.time.now.yyyymmdd_hhmmss_sss;

import java.text.SimpleDateFormat;
import java.util.Date;

import a.framework.*;

public class EntityImpl implements Entity, G {
	public String creationDate() {return "20240618";}

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS");
	
	public Object g() throws Exception
	{return sdf.format(new Date());}
}
