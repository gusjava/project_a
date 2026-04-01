package a.entity.gus06.list.map.builder1.with;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160819";}


	private Service listBuilder;

	public EntityImpl() throws Exception
	{
		listBuilder = Outside.service(this,"gus06.list.map.builder1");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		P p = (P) listBuilder.t(o[0]);
		List data = (List) o[1];
		
		for(Object el:data) p.p(el);
		return p;
	}
}
