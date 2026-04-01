package a.entity.gus06.sys.parser3.resolver1.list.tostring;

import a.framework.*;
import java.util.List;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160225";}
	
	public static final String VALUE = "value";
	
	
	
	public Object t(Object obj) throws Exception
	{
		List l = (List) obj;
		int n = l.size();
		
		StringBuffer b = new StringBuffer();
		for(int i=0;i<n;i++)
		{
			Map m = (Map) l.get(i);
			b.append(m.get(VALUE));
			if(i<n-1) b.append(" ");
		}
		return b.toString();
	}
}
