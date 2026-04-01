package a.entity.gus06.sys.expression1.apply.op._excludeall_vst_n;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151125";}


	private Service builder;
	private Service filterInv;
	private Service perform;
	
	
	public EntityImpl() throws Exception
	{
		builder = Outside.service(this,"gus06.filter.string.build.startswith_n");
		filterInv = Outside.service(this,"gus06.feature.op.filter.inv");
		perform = Outside.service(this,"gus06.map.value.filter");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof Map) return new T1(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class T1 implements T
	{
		private Object data;
		public T1(Object data) {this.data = data;}
		
		public Object t(Object obj) throws Exception
		{
			F f = (F) builder.t(obj);
			f = (F) filterInv.t(f);
			return perform.t(new Object[]{data,f});
		}
	}
}
