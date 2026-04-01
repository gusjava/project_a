package a.entity.gus06.feature.op.pipenext.p;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161212";}


	private Service pe;
	private Service pg;
	
	public EntityImpl() throws Exception
	{
		pe = Outside.service(this,"gus06.feature.wrap.pe.p");
		pg = Outside.service(this,"gus06.feature.wrap.pg.t");
	}


	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		P p = (P) o[0];
		
		if(o[1] instanceof E) return pe.t(o);
		if(o[1] instanceof G) return pg.t(o);
		
		throw new Exception("Invalid data type: "+o[1].getClass().getName());
	}
}
