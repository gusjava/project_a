package a.entity.gus06.list.mapvalue3;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170327";}


	private Service buildMap;
	
	public EntityImpl() throws Exception
	{
		buildMap = Outside.service(this,"gus06.list.findall3.buildmap");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List input = (List) o[0];
		T t = (T) o[1];
		
		Map output = new HashMap();
		int nb = input.size();
		
		for(int i=0;i<nb;i++)
		{
			Object element = input.get(i);
			
			Integer index = Integer.valueOf(i);
			Map m = (Map) buildMap.t(new Object[]{input,index});
			
			Object key = element;
			Object value = t.t(m);
			
			output.put(key,value);
		}
		return output;
	}
}
