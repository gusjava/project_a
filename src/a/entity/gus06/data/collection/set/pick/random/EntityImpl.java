package a.entity.gus06.data.collection.set.pick.random;

import a.framework.*;
import java.util.Set;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150418";}
	
	
	public Object t(Object obj) throws Exception
	{
		Set set = (Set) obj;
		int size = set.size();
		if(size==0) return null;
		if(size==1) return set.iterator().next();
		
		int index = (int) (Math.random()*size);
		Iterator it = set.iterator();
		for(int i=0;i<index;i++) it.next();
		return it.next();
	}
}
