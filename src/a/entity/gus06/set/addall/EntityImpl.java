package a.entity.gus06.set.addall;

import a.framework.*;
import java.util.Set;
import java.util.Collection;
import java.util.HashSet;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20160128";}
	
	
	
	private Service findSet;
	
	public EntityImpl() throws Exception
	{
		findSet = Outside.service(this,"gus06.find.set");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Set set = (Set) o[0];
		Set data = (Set) findSet.t(o[1]);
		
		set.addAll(data);
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Set set = (Set) o[0];
		Set data = (Set) findSet.t(o[1]);
		
		Set set1 = new HashSet(set);
		set1.addAll(data);
		
		return set1;
	}
}
