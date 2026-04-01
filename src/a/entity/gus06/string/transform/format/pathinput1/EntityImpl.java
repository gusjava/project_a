package a.entity.gus06.string.transform.format.pathinput1;

import a.framework.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151022";}

	
	
	public Object t(Object obj) throws Exception
	{return format((String) obj);}
	
	
	
	private String format(String s)
	{
		s = s.replace("\\",File.separator);
		s = s.replace("/",File.separator);
		s = s.replace("::",now());
		s = s.replace(":",today());
		return s;
	}
	
	private SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
	private SimpleDateFormat yyyyMMdd_HHmmss = new SimpleDateFormat("yyyyMMdd_HHmmss");
	
	private String today() {return yyyyMMdd.format(new Date());}
	private String now() {return yyyyMMdd_HHmmss.format(new Date());}
}
