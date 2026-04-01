package a.entity.gus06.sys.cmd1.builder.op.e;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150626";}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=1) throw new Exception("Wrong data number: "+o.length);
		
		E e = (E) o[0];
		
		e.e();
	}
}
