package a.entity.gus06.sys.expression1.apply.op._wrap_t;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151122";}


	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		return new Wrap(obj);
	}
	
	public class Wrap implements T
	{
		private Object data;
		public Wrap(Object data) {this.data = data;}
		
		public Object t(Object obj) throws Exception
		{return data;}
	}
}
