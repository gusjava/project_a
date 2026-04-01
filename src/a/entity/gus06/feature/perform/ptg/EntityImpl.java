package a.entity.gus06.feature.perform.ptg;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150707";}

	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		P p = (P) o[0];
		T t = (T) o[1];
		G g = (G) o[2];
		
		p.p(t.t(g.g()));
	}
}
