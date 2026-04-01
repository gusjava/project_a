package a.entity.gus06.sys.expression1.builder1.h;

import a.framework.*;
import java.util.List;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151112";}

	private Service prepare;
	private Service resolver1;
	private Service buildExternal;

	public EntityImpl() throws Exception
	{
		prepare = Outside.service(this,"gus06.sys.parser3.prepare");
		resolver1 = Outside.service(this,"gus06.sys.parser3.resolver1");
		buildExternal = Outside.service(this,"gus06.sys.expression1.external.c1");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String exp = (String) o[0];
		Map opMap = (Map) o[1];
		
		List list = (List) prepare.t(exp);
		return new H1(list,opMap);
	}
	
	
	
	private class H1 implements H
	{
		private List list;
		private Map opMap;
		
		public H1(List list, Map opMap)
		{
			this.list = list;
			this.opMap = opMap;
		}
		
		public double h(double value) throws Exception
		{
			T external = buildExternal(Double.valueOf(value),opMap);
			T t = (T) resolver1.t(external);
			Number r = (Number) t.t(list);
			return r.doubleValue();
		}
	}
	
	private T buildExternal(Object obj, Map opMap) throws Exception
	{return (T) buildExternal.t(new Object[]{obj,opMap});}
}
