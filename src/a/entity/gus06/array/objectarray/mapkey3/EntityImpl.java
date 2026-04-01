package a.entity.gus06.array.objectarray.mapkey3;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170327";}


	private Service buildMap;
	
	public EntityImpl() throws Exception
	{
		buildMap = Outside.service(this,"gus06.array.objectarray.findall3.buildmap");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object[] input = (Object[]) o[0];
		T t = (T) o[1];
		
		Map output = new HashMap();
		int nb = input.length;
		
		for(int i=0;i<nb;i++)
		{
			Object element = input[i];
			
			Integer index = Integer.valueOf(i);
			Map m = (Map) buildMap.t(new Object[]{input,index});
			
			Object key = t.t(m);
			Object value = element;
			
			output.put(key,value);
		}
		return output;
	}
}
