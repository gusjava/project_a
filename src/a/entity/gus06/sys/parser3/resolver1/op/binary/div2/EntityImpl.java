package a.entity.gus06.sys.parser3.resolver1.op.binary.div2;

import a.framework.*;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151030";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List cut = (List) o[0];
		T t = (T) o[1];
		
		if(cut.size()!=2) throw new Exception("Invalid split for equals operation: "+cut.size());
		
		Object o1 = t.t(cut.get(0));
		Object o2 = t.t(cut.get(1));
		
		if(!(o1 instanceof Number)) throw new Exception("Invalid data type for o1: "+o1.getClass().getName());
		if(!(o2 instanceof Number)) throw new Exception("Invalid data type for o2: "+o2.getClass().getName());
		
		if(o1 instanceof Integer && o2 instanceof Integer)
		{
			int n1 = ((Integer) o1).intValue();
			int n2 = ((Integer) o2).intValue();
			return Integer.valueOf(n1/n2);
		}
		
		long n1 = ((Number) o1).longValue();
		long n2 = ((Number) o2).longValue();
		return Long.valueOf(n1/n2);
	}
}
