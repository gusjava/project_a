package a.entity.gus06.sys.opposite1.set.sum;

import a.framework.*;
import java.util.Set;
import java.util.HashSet;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160721";}


	private Service check;
	
	public EntityImpl() throws Exception
	{
		check = Outside.service(this,"gus06.sys.opposite1.check");
	}
	

	public Object t(Object obj) throws Exception
	{
		Set[] ss = (Set[]) obj;
		
		Set set1 = new HashSet();
		Set set2 = new HashSet();
		
		for(Set s:ss)
		{
			Set set = check.f(s) ? set2 : set1;
			set.addAll(s);
		}
		set1.removeAll(set2);
		return set1;
	}
}
