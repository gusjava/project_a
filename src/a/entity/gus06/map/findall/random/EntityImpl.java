package a.entity.gus06.map.findall.random;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170506";}


	private Service ruleToIndex;
	
	public EntityImpl() throws Exception
	{
		ruleToIndex = Outside.service(this,"gus06.list.ruletoindex");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map input = (Map) o[0];
		int nb = ((Integer) ruleToIndex.t(obj)).intValue();
		
		int size = input.size();
		List input1 = new ArrayList(input.keySet());
		Map output = new HashMap();
		
		while(output.size()<nb)
		{
			Integer n = Integer.valueOf(random(size));
			Object key = input1.get(n.intValue());
			output.put(key,input.get(key));
		}
		return output;
	}
	
	
	
	private int random(int limit)
	{return (int) (Math.random()*limit);}
}
