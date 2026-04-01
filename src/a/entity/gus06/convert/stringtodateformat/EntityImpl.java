package a.entity.gus06.convert.stringtodateformat;

import a.framework.*;
import java.text.SimpleDateFormat;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161120";}


	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		return new SimpleDateFormat(s);
	}
}
