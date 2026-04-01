package a.entity.gus06.sys.expression1.apply.op._app_restart_args;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170410";}


	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.app.restart");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof String) return new E1(obj);
		if(obj instanceof String[]) return new E1(obj);
		if(obj instanceof List) return new E1(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class E1 implements E
	{
		private Object args;
		public E1(Object args) {this.args = args;}
		
		public void e() throws Exception
		{perform.p(args);}
	}
}
