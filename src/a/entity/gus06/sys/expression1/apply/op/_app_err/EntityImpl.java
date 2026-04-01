package a.entity.gus06.sys.expression1.apply.op._app_err;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170411";}


	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		
		if(obj==null) return null;
		if(obj instanceof String)	return new E0(new Exception((String) obj));
		if(obj instanceof Exception)	return new E0((Exception) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private class E0 implements E
	{
		private Exception e;
		public E0(Exception e) {this.e = e;}
		
		public void e() throws Exception
		{send(e);}
	}
	
	private void send(Exception e)
	{Outside.err(this,"send(Exception)",e);}
}
