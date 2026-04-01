package a.entity.gus06.list.findall3;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170112";}


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
		F filter = (F) o[1];
		
		List output = new ArrayList();
		int nb = input.size();
		
		for(int i=0;i<nb;i++)
		{
			Integer index = Integer.valueOf(i);
			Map m = (Map) buildMap.t(new Object[]{input,index});
			if(filter.f(m)) output.add(input.get(i));
		}
		return output;
	}
}
