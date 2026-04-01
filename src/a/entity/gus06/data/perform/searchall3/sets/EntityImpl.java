package a.entity.gus06.data.perform.searchall3.sets;

import a.framework.*;
import java.util.Set;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180411";}
	
	
	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.data.perform.searchall3");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object input = o[0];
		F f = (F) o[1];
		
		return perform.t(new Object[]{input,new F1(f)});
	}
	
	
	
	private class F1 implements F
	{
		private F f;
		public F1(F f) {this.f = f;}
		
		public boolean f(Object obj) throws Exception
		{
			Map info = (Map) obj;
			Object element = info.get("c");
			return element instanceof Set && f.f(obj);
		}
	}
}
