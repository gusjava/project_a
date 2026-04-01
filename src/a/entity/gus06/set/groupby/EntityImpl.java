package a.entity.gus06.set.groupby;

import a.framework.*;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160821";}


	private Service putInSet;
	
	public EntityImpl() throws Exception
	{
		putInSet = Outside.service(this,"gus06.map.put.inset");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Set input = (Set) o[0];
		T t = (T) o[1];
		
		Map output = new HashMap();
		for(Object elem:input)
		{
			Object elem1 = t.t(elem);
			putInSet.p(new Object[]{output,elem1,elem});
		}
		return output;
	}
}
