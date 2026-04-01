package a.entity.gus06.set.each;

import a.framework.*;
import java.util.Set;
import java.util.Iterator;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20151125";}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Set input = (Set) o[0];
		P p = (P) o[1];
		
		Iterator it = input.iterator();
		while(it.hasNext()) p.p(it.next());
	}
}
