package a.entity.gus06.sys.expression1.apply.op._charset_list;

import a.framework.*;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160310";}

	public static final String T = "constant";


	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		List list = new ArrayList();
		Map m = Charset.availableCharsets();
		Iterator it = m.keySet().iterator();
		while(it.hasNext())
		{
			Object key = it.next();
			Object value = m.get(key);
			list.add(value);
		}
		
		return list;
	}
}
