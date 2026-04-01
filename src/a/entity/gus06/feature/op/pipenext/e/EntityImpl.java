package a.entity.gus06.feature.op.pipenext.e;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161212";}


	private Service ee;
	private Service ef;
	private Service eg;
	private Service eh;
	private Service ei;
	private Service ep;
	private Service er;
	private Service et;
	
	private Service loop_e;
	
	public EntityImpl() throws Exception
	{
		ee = Outside.service(this,"gus06.feature.wrap.ee.e");
		ef = Outside.service(this,"gus06.feature.wrap.ef.f");
		eg = Outside.service(this,"gus06.feature.wrap.eg.g");
		eh = Outside.service(this,"gus06.feature.wrap.eh.h");
		ei = Outside.service(this,"gus06.feature.wrap.ei.i");
		ep = Outside.service(this,"gus06.feature.wrap.ep.p");
		er = Outside.service(this,"gus06.feature.wrap.er.r");
		et = Outside.service(this,"gus06.feature.wrap.et.t");
		
		loop_e = Outside.service(this,"gus06.feature.op.loop.e");
	}


	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		E e = (E) o[0];
		
		if(o[1] instanceof E) return ee.t(o);
		if(o[1] instanceof F) return ef.t(o);
		if(o[1] instanceof G) return eg.t(o);
		if(o[1] instanceof H) return eh.t(o);
		if(o[1] instanceof I) return ei.t(o);
		if(o[1] instanceof P) return ep.t(o);
		if(o[1] instanceof R) return er.t(o);
		if(o[1] instanceof T) return et.t(o);
		
		if(o[1] instanceof Integer) return loop_e.t(o);
		
		throw new Exception("Invalid data type: "+o[1].getClass().getName());
	}
}
