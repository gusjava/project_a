package a.entity.gus06.data.compare.map.completekeyvalue;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151201";}



	public Object t(Object obj) throws Exception
	{
		Map[] mm = (Map[]) obj;
		if(mm.length<2) throw new Exception("Wrong data number: "+mm.length);
		
		Map output = new HashMap(mm[0]);
		Iterator it = output.keySet().iterator();
		while(it.hasNext())
		{
			Object key = it.next();
			Object value = output.get(key);
			
			if(!isEverywhere(mm,key,value)) it.remove();
		}
		return output;
	}
	
	
	private boolean isEverywhere(Map[] mm, Object key, Object value)
	{
		for(int i=1;i<mm.length;i++)
		{
			Map m = mm[i];
			if(!m.containsKey(key)) return false;
			if(!m.get(key).equals(value)) return false;
		}
		return true;
	}
}
