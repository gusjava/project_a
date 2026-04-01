package a.entity.gus06.data.list.tryandsort;

import a.framework.*;
import java.util.List;
import java.util.Collections;

public class EntityImpl implements Entity, F, P {

	public String creationDate() {return "20231016";}


	private Service getIdentical;
	
	public EntityImpl() throws Exception
	{
		getIdentical = Outside.service(this,"gus06.data.filter.eachtype.getidentical");
	}

	
	public void p(Object obj) throws Exception
	{f(obj);}
	
		
	public boolean f(Object obj) throws Exception
	{
		List list = (List) obj;
		if(list.size()<2) return false;
		if(list.contains(null)) return false;
		
		Class c = (Class) getIdentical.t(list);
		if(c==null) return false;
		if(!Comparable.class.isAssignableFrom(c)) return false;
		
		Collections.sort(list);
		return true;
	}
}