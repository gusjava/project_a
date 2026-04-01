package a.entity.gus06.list.build.from.string1;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250824";}


	private Service evaluate;

	public EntityImpl() throws Exception
	{
		evaluate = Outside.service(this,"gus06.sys.parser3.evaluate");
	}
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		if(!s.startsWith("[")) s = "["+s;
		if(!s.endsWith("]")) s = s+"]";
		
		List list = (List) evaluate.t(s);
		return list;
	}
}
