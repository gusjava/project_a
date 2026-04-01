package a.entity.gus06.feature.op.pipenext.i;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161210";}


	private Service ie;
	private Service ip;
	private Service it;
	
	public EntityImpl() throws Exception
	{
		ie = Outside.service(this,"gus06.feature.wrap.ie.i");
		ip = Outside.service(this,"gus06.feature.wrap.ip.e");
		it = Outside.service(this,"gus06.feature.wrap.it.i");
	}


	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		I i = (I) o[0];
		
		if(o[1] instanceof E) return ie.t(o);
		if(o[1] instanceof P) return ip.t(o);
		if(o[1] instanceof T) return it.t(o);
		
		throw new Exception("Invalid data type: "+o[1].getClass().getName());
	}
}
