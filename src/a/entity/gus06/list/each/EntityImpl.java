package a.entity.gus06.list.each;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20151125";}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List input = (List) o[0];
		P p = (P) o[1];
		
		int nb = input.size();
		for(int i=0;i<nb;i++) p.p(input.get(i));
	}
}
