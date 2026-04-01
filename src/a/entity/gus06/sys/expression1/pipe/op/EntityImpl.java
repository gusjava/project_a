package a.entity.gus06.sys.expression1.pipe.op;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170119";}


	private Service tt;
	private Service ft;
	private Service ht;
	private Service gt;
	private Service it;

	public EntityImpl() throws Exception
	{
		tt = Outside.service(this,"gus06.feature.wrap.tt.t");
		ft = Outside.service(this,"gus06.feature.wrap.ft.t");
		ht = Outside.service(this,"gus06.feature.wrap.ht.t");
		gt = Outside.service(this,"gus06.feature.wrap.gt.g");
		it = Outside.service(this,"gus06.feature.wrap.it.i");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		T op = (T) o[0];
		Object[] params = (Object[]) o[1];
		
		if(params.length!=2) throw new Exception("Wrong params number: "+params.length);
		Object input = params[0];
		Map opMap = (Map) params[1];
		
		T t0 = new T0(op,opMap);
		
		if(input instanceof T) return tt.t(new Object[]{input,t0});
		if(input instanceof F) return ft.t(new Object[]{input,t0});
		if(input instanceof H) return ht.t(new Object[]{input,t0});
		if(input instanceof G) return gt.t(new Object[]{input,t0});
		if(input instanceof I) return it.t(new Object[]{input,t0});
		
		throw new Exception("Invalid data type: "+input.getClass().getName());
	}
	
	
	
	private class T0 implements T
	{
		private T t;
		private Map opMap;
		
		public T0(T t, Map opMap)
		{this.t = t;this.opMap = opMap;}
		
		public Object t(Object obj) throws Exception
		{return t.t(new Object[]{obj,opMap});}
	}
}
