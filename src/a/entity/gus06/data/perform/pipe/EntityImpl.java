package a.entity.gus06.data.perform.pipe;

import a.framework.*;
import java.util.List;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161210";}
	
	
	
	private Service pipe_e;
	private Service pipe_t;
	private Service pipe_h;
	private Service pipe_map;
	
	private Service loop_e;
	private Service loop_t;
	private Service loop_h;
	private Service loop_map;
	
	private Service toArray_e;
	private Service toArray_t;
	private Service toArray_h;
	private Service toArray_map;
	
	private Service next_e;
	private Service next_g;
	private Service next_p;
	private Service next_i;
	private Service next_t;
	private Service next_f;
	private Service next_h;
	private Service next_map;


	public EntityImpl() throws Exception
	{
		pipe_e = Outside.service(this,"gus06.feature.op.pipe.e");
		pipe_t = Outside.service(this,"gus06.feature.op.pipe.t");
		pipe_h = Outside.service(this,"gus06.feature.op.pipe.h");
		pipe_map = Outside.service(this,"gus06.feature.op.pipe.map");
		
		loop_e = Outside.service(this,"gus06.feature.op.loop.e");
		loop_t = Outside.service(this,"gus06.feature.op.loop.t");
		loop_h = Outside.service(this,"gus06.feature.op.loop.h");
		loop_map = Outside.service(this,"gus06.feature.op.loop.map");
		
		toArray_e = Outside.service(this,"gus06.convert.objarraytoearray.strict");
		toArray_t = Outside.service(this,"gus06.convert.objarraytotarray.strict");
		toArray_h = Outside.service(this,"gus06.convert.objarraytoharray.strict");
		toArray_map = Outside.service(this,"gus06.convert.objarraytomaparray.strict");
		
		next_e = Outside.service(this,"gus06.feature.op.pipenext.e");
		next_g = Outside.service(this,"gus06.feature.op.pipenext.g");
		next_p = Outside.service(this,"gus06.feature.op.pipenext.p");
		next_i = Outside.service(this,"gus06.feature.op.pipenext.i");
		next_t = Outside.service(this,"gus06.feature.op.pipenext.t");
		next_f = Outside.service(this,"gus06.feature.op.pipenext.f");
		next_h = Outside.service(this,"gus06.feature.op.pipenext.h");
		next_map = Outside.service(this,"gus06.feature.op.pipenext.map");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof E[]) return pipe_e.t(obj);
		if(obj instanceof T[]) return pipe_t.t(obj);
		if(obj instanceof H[]) return pipe_h.t(obj);
		if(obj instanceof Map[]) return pipe_map.t(obj);
		
		if(obj instanceof Object[]) return handleArray((Object[]) obj);
		if(obj instanceof List) return handleArray(toArray((List) obj));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private Object[] toArray(List l)
	{return l.toArray(new Object[l.size()]);}
	
	
	
	private Object handleArray(Object[] oo) throws Exception
	{
		if(oo.length==0) throw new Exception("Invalid array length: "+oo.length);
		if(oo.length==1) return oo[1];
		
		if(oo.length==2 && oo[1] instanceof Integer)
		{
			if(oo[0] instanceof E) return loop_e.t(oo);
			if(oo[0] instanceof T) return loop_t.t(oo);
			if(oo[0] instanceof H) return loop_h.t(oo);
			if(oo[0] instanceof Map) return loop_map.t(oo);
			
			throw new Exception("Invalid pipe chain");
		}
		
		E[] ee = (E[]) toArray_e.t(oo);
		if(ee!=null) return pipe_e.t(ee);
		
		T[] tt = (T[]) toArray_t.t(oo);
		if(tt!=null) return pipe_t.t(tt);
		
		H[] hh = (H[]) toArray_h.t(oo);
		if(hh!=null) return pipe_h.t(hh);
		
		Map[] maps = (Map[]) toArray_map.t(oo);
		if(maps!=null) return pipe_map.t(maps);
		
		
		Object r = oo[0];
		int length = oo.length;
		
		for(int i=1;i<length;i++)
		r = handleNext(r,oo[i]);
		
		return r;
	}
	
	
	
	private Object handleNext(Object r1, Object r2) throws Exception
	{
		if(r1 instanceof E) return next_e.t(new Object[]{r1,r2});
		if(r1 instanceof G) return next_g.t(new Object[]{r1,r2});
		if(r1 instanceof P) return next_p.t(new Object[]{r1,r2});
		if(r1 instanceof I) return next_i.t(new Object[]{r1,r2});
		if(r1 instanceof T) return next_t.t(new Object[]{r1,r2});
		if(r1 instanceof F) return next_f.t(new Object[]{r1,r2});
		if(r1 instanceof H) return next_h.t(new Object[]{r1,r2});
		if(r1 instanceof Map) return next_map.t(new Object[]{r1,r2});
		
		throw new Exception("Invalid data type: "+r1.getClass().getName());
	}
}
