package a.entity.gus06.set.replaceall;

import a.framework.*;
import java.util.Set;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20170429";}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Set set = (Set) o[0];
		Set s = (Set) o[1];
		
		set.clear();
		set.addAll(s);
	}
}
