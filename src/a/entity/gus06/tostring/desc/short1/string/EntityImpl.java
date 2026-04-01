package a.entity.gus06.tostring.desc.short1.string;

import a.framework.*;
import java.util.Collection;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151129";}
	
	public static final int STR_LIMIT = 60;


	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
	
		s = s.replace("\\","\\\\").
		replace("\n","\\n").
		replace("\t","\\t").
		replace("\r","\\r");
		
		if(s.length()>STR_LIMIT)
			s = s.substring(0,STR_LIMIT)+"...";
		return "\""+s+"\"";
	}
}
