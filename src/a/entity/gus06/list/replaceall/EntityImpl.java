package a.entity.gus06.list.replaceall;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20170429";}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List list = (List) o[0];
		List l = (List) o[1];
		
		list.clear();
		list.addAll(l);
	}
}
