package a.entity.gus06.set.build.from.g;

import a.framework.*;
import java.util.Set;
import java.util.HashSet;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170225";}

	
	
	public Object t(Object obj) throws Exception
	{
		G g = (G) obj;
		Set set = new HashSet();
		Object value = g.g();
		while(value!=null)
		{
			set.add(value);
			value = g.g();
		}
		return set;
	}
}
