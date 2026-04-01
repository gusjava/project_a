package a.entity.gus06.data.compare.map.completekey;

import a.framework.*;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151202";}



	public Object t(Object obj) throws Exception
	{
		Map[] mm = (Map[]) obj;
		if(mm.length<2) throw new Exception("Wrong data number: "+mm.length);
		
		Set output = new HashSet(mm[0].keySet());
		Iterator it = output.iterator();
		while(it.hasNext())
		{
			Object key = it.next();
			if(!isEverywhere(mm,key)) it.remove();
		}
		return output;
	}
	
	
	private boolean isEverywhere(Map[] mm, Object key)
	{
		for(int i=1;i<mm.length;i++)
		{
			Map m = mm[i];
			if(!m.containsKey(key)) return false;
		}
		return true;
	}
}
