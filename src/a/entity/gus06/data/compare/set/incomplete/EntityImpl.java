package a.entity.gus06.data.compare.set.incomplete;

import a.framework.*;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151202";}



	public Object t(Object obj) throws Exception
	{
		Set[] ss = (Set[]) obj;
		if(ss.length<2) throw new Exception("Wrong data number: "+ss.length);
		
		Set output = new HashSet();
		for(Set s:ss) output.addAll(s);
		
		Iterator it = output.iterator();
		while(it.hasNext())
		{
			Object o = it.next();
			if(isEverywhere(ss,o)) it.remove();
		}
		return output;
	}
	
	
	private boolean isEverywhere(Set[] ss, Object key)
	{
		for(int i=0;i<ss.length;i++)
			if(!ss[i].contains(key)) return false;
		return true;
	}
}
