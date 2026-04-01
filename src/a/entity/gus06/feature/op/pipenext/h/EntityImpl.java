package a.entity.gus06.feature.op.pipenext.h;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161210";}


	private Service he;
	private Service hh;
	private Service ht;
	
	private Service loop_h;
	
	public EntityImpl() throws Exception
	{
		he = Outside.service(this,"gus06.feature.wrap.he.h");
		hh = Outside.service(this,"gus06.feature.wrap.hh.h");
		ht = Outside.service(this,"gus06.feature.wrap.ht.t");
		
		loop_h = Outside.service(this,"gus06.feature.op.loop.h");
	}


	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		H h = (H) o[0];
		
		if(o[1] instanceof E) return he.t(o);
		if(o[1] instanceof H) return hh.t(o);
		if(o[1] instanceof T) return ht.t(o);
		
		if(o[1] instanceof Integer) return loop_h.t(o);
		
		throw new Exception("Invalid data type: "+o[1].getClass().getName());
	}
}
