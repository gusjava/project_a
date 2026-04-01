package a.entity.gus06.sys.parser3.resolver1.op.binary.equals;

import a.framework.*;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151029";}


	private Service compare;

	public EntityImpl() throws Exception
	{
		compare = Outside.service(this,"gus06.data.compare.o1");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List cut = (List) o[0];
		T t = (T) o[1];
		
		if(cut.size()!=2) throw new Exception("Invalid split for equals operation: "+cut.size());
		
		Object o1 = t.t(cut.get(0));
		Object o2 = t.t(cut.get(1));
		
		return Boolean.valueOf(equals(o1,o2));
	}
	
	private boolean equals(Object o1, Object o2) throws Exception
	{return compare.f(new Object[]{o1,o2});}
}
