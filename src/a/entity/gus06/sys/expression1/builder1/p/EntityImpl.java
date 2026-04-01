package a.entity.gus06.sys.expression1.builder1.p;

import a.framework.*;
import java.util.List;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160711";}

	private Service prepare;
	private Service resolver1;
	private Service buildExternal1;
	private Service buildExternal2;

	public EntityImpl() throws Exception
	{
		prepare = Outside.service(this,"gus06.sys.parser3.prepare");
		resolver1 = Outside.service(this,"gus06.sys.parser3.resolver1");
		buildExternal1 = Outside.service(this,"gus06.sys.expression1.external.c1");
		buildExternal2 = Outside.service(this,"gus06.sys.expression1.external.c2");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String exp = (String) o[0];
		Map opMap = (Map) o[1];
		
		boolean mMode = exp.startsWith(":");
		if(mMode) exp = exp.substring(1);
		
		List list = (List) prepare.t(exp);
		return new P1(list,opMap,mMode);
	}
	
	
	
	private class P1 implements P
	{
		private List list;
		private Map opMap;
		private boolean mMode;
		
		public P1(List list, Map opMap, boolean mMode)
		{
			this.list = list;
			this.opMap = opMap;
			this.mMode = mMode;
		}
		
		public void p(Object obj) throws Exception
		{
			T external = buildExternal(obj,opMap,mMode);
			T t = (T) resolver1.t(external);
			E e = (E) t.t(list);
			e.e();
		}
	}
	
	
	private T buildExternal(Object obj, Map opMap, boolean mMode) throws Exception
	{
		if(!mMode) return (T) buildExternal1.t(new Object[]{obj,opMap});
		
		if(!(obj instanceof Map)) throw new Exception("Input data should be a Map (mMode activated). Data type="+obj.getClass().getName());
		return (T) buildExternal2.t(new Object[]{obj,opMap});
	}
}
