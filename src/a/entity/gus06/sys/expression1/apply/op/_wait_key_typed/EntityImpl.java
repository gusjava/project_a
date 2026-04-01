package a.entity.gus06.sys.expression1.apply.op._wait_key_typed;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160418";}


	private Service buffer;
	
	public EntityImpl() throws Exception
	{
		buffer = Outside.service(this,"gus06.jna.keyboard.buffer");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof String) return new E1((String) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private class E1 implements E
	{
		private String key;
		
		public E1(String key)
		{this.key = key;}
		
		public void e() throws Exception
		{
			while(buffer.f(key)) {sleep_5();}
			while(!buffer.f(key)) {sleep_5();}
			while(buffer.f(key)) {sleep_5();}
		}
	}
	
	
	private void sleep_5()
	{
		try{Thread.sleep(5);}
		catch(Exception e)
		{Outside.err(this,"sleep_5()",e);}
	}
}
