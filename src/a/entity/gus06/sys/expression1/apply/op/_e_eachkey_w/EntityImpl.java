package a.entity.gus06.sys.expression1.apply.op._e_eachkey_w;

import a.framework.*;
import java.util.Map;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161115";}


	private Service builderP;
	private Service builderF;
	private Service perform;
	private Service wrap;
	
	
	public EntityImpl() throws Exception
	{
		builderP = Outside.service(this,"gus06.sys.expression1.builder2.p");
		builderF = Outside.service(this,"gus06.sys.expression1.builder2.f");
		perform = Outside.service(this,"gus06.data.perform.eachkey");
		wrap = Outside.service(this,"gus06.feature.wrap.fp.p");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		Map opMap = (Map) o[1];
		
		if(value==null) return null;
		
		if(value instanceof Map) return new T1(value,opMap);
		if(value instanceof File) return new T1(value,opMap);
		
		throw new Exception("Invalid data type: "+value.getClass().getName());
	}
	
	
	private class T1 implements T
	{
		private Object value;
		private Map opMap;
		
		public T1(Object value, Map opMap)
		{
			this.value = value;
			this.opMap = opMap;
		}
		
		public Object t(Object obj) throws Exception
		{
			P p = (P) builderP.t(new Object[]{obj,opMap});
			return new T2(value,p,opMap);
		}
	}
	
	
	private class T2 implements T
	{
		private Object value;
		private P p;
		private Map opMap;
		
		public T2(Object value, P p, Map opMap)
		{
			this.value = value;
			this.p = p;
			this.opMap = opMap;
		}
		
		public Object t(Object obj) throws Exception
		{
			F f = (F) builderF.t(new Object[]{obj,opMap});
			P p1 = (P) wrap.t(new Object[]{f,p});
			return new E1(new Object[]{value,p1});
		}
	}
	
	
	private class E1 implements E
	{
		private Object o;
		public E1(Object o){this.o = o;}
		
		public void e() throws Exception
		{perform.p(o);}
	}
}
