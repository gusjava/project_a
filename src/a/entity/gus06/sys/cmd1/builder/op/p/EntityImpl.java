package a.entity.gus06.sys.cmd1.builder.op.p;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150626";}

	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		P p = (P) o[0];
		
		p.p(o[1]);
	}
}
