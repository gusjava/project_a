package a.entity.gus06.data.perform.searchall2.strings;

import a.framework.*;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180408";}
	
	
	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.data.perform.searchall2");
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
			Object[] info = (Object[]) obj;
			Object element = info[0];
			return element instanceof String && f.f(obj);
		}
	}
}
