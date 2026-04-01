package a.entity.gus06.sys.expression1.apply.op._operator_names_const;

import a.framework.*;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.lang.reflect.Field;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160814";}

	public static final String T = "constant";
	
	public static final String LINE = "public static final String T = \"constant\"";
	
	
	private Service findSrc;

	public EntityImpl() throws Exception
	{
		findSrc = Outside.service(this,"gus06.sys.expression1.apply.opdata.src");
	}

	

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		Map opMap = (Map) o[1];
		
		Set set = new HashSet();
		
		Iterator it = opMap.keySet().iterator();
		while(it.hasNext())
		{
			String name = (String) it.next();
			if(!name.equals("__context"))
			{
				String src = (String) findSrc.t(name);
				if(src.contains(LINE)) set.add(name);
			}
		}
		
		return set;
	}
}
