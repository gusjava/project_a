package a.entity.gus06.list.build.from.g;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170225";}
	
	public static int  LIMIT = 100000;

	
	
	public Object t(Object obj) throws Exception
	{
		G g = (G) obj;
		List list = new ArrayList();
		Object value = g.g();
		
		int k = 0;
		while(value!=null)
		{
			k++;
			if(k>LIMIT) throw new Exception("Limit exceeded: "+k);
			list.add(value);
			value = g.g();
		}
		return list;
	}
}
