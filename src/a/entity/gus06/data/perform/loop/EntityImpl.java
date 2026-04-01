package a.entity.gus06.data.perform.loop;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160305";}


	private Service loop_e;
	private Service loop_g;
	private Service loop_h;
	private Service loop_p;
	private Service loop_t;


	public EntityImpl() throws Exception
	{
		loop_e = Outside.service(this,"gus06.feature.op.loop.e");
		loop_g = Outside.service(this,"gus06.feature.op.loop.g");
		loop_h = Outside.service(this,"gus06.feature.op.loop.h");
		loop_p = Outside.service(this,"gus06.feature.op.loop.p");
		loop_t = Outside.service(this,"gus06.feature.op.loop.t");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object data = o[0];
		Object times = o[1];
		
		if(data instanceof E) return loop_e.t(obj);
		if(data instanceof G) return loop_g.t(obj);
		if(data instanceof H) return loop_h.t(obj);
		if(data instanceof P) return loop_p.t(obj);
		if(data instanceof T) return loop_t.t(obj);
		
		throw new Exception("Invalid data type: "+data.getClass().getName());
	}
}
