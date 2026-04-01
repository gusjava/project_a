package a.entity.gus06.data.compare.set.complete;

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
		
		Set output = new HashSet(ss[0]);
		Iterator it = output.iterator();
		while(it.hasNext())
		{
			Object o = it.next();
			if(!isEverywhere(ss,o)) it.remove();
		}
		return output;
	}
	
	
	private boolean isEverywhere(Set[] ss, Object o)
	{
		for(int i=1;i<ss.length;i++)
			if(!ss[i].contains(o)) return false;
		return true;
	}
}
