package a.entity.gus06.feature.op.pipenext.t;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161210";}


	private Service te;
	private Service tf;
	private Service th;
	private Service tmap;
	private Service tp;
	private Service tr;
	private Service tt;
	
	private Service loop_t;
	
	public EntityImpl() throws Exception
	{
		te = Outside.service(this,"gus06.feature.wrap.te.t");
		tf = Outside.service(this,"gus06.feature.wrap.tf.f");
		th = Outside.service(this,"gus06.feature.wrap.th.t");
		tmap = Outside.service(this,"gus06.feature.wrap.tmap.t");
		tp = Outside.service(this,"gus06.feature.wrap.tp.p");
		tr = Outside.service(this,"gus06.feature.wrap.tr.t");
		tt = Outside.service(this,"gus06.feature.wrap.tt.t");
		
		loop_t = Outside.service(this,"gus06.feature.op.loop.t");
	}


	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		T t = (T) o[0];
		
		if(o[1] instanceof E) return te.t(o);
		if(o[1] instanceof F) return tf.t(o);
		if(o[1] instanceof H) return th.t(o);
		if(o[1] instanceof Map) return tmap.t(o);
		if(o[1] instanceof P) return tp.t(o);
		if(o[1] instanceof R) return tr.t(o);
		if(o[1] instanceof T) return tt.t(o);
		
		if(o[1] instanceof Integer) return loop_t.t(o);
		
		throw new Exception("Invalid data type: "+o[1].getClass().getName());
	}
}
