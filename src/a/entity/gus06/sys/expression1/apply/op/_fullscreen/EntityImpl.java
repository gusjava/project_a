package a.entity.gus06.sys.expression1.apply.op._fullscreen;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160505";}


	private Service fullScreen;
	
	
	public EntityImpl() throws Exception
	{
		fullScreen = Outside.service(this,"gus06.sys.fullscreen1.main");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		return new E1(obj);
	}
	
	
	
	private class E1 implements E
	{
		private Object data;
		public E1(Object data) {this.data = data;}
		
		public void e() throws Exception
		{fullScreen.p(data);}
	}
}
