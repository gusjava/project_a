package a.entity.gus06.set.keep;

import a.framework.*;
import java.util.Set;
import java.util.HashSet;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20170419";}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Set set = (Set) o[0];
		Object element = o[1];
		
		if(!set.contains(element)) return;
		
		set.clear();
		set.add(element);
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Set set = (Set) o[0];
		Object element = o[1];
		
		Set set1 = new HashSet();
		if(!set.contains(element)) return set1;
		
		set1.add(element);
		return set1;
	}
}
