package a.entity.gus06.sys.expression1.apply.op._wait_mouse_bm_released;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200113";}

	public static final String T = "constant";
	public static final String KEY = "M";


	private Service support;
	
	public EntityImpl() throws Exception
	{
		support = Outside.service(this,"gus06.jna.mouse.support");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		return new E1();
	}
	
	
	
	private class E1 implements E
	{
		public void e() throws Exception
		{
			while(!support.f(KEY)) {sleep_5();}
			while(support.f(KEY)) {sleep_5();}
		}
	}
	
	
	private void sleep_5()
	{
		try{Thread.sleep(5);}
		catch(Exception e)
		{Outside.err(this,"sleep_5()",e);}
	}
}
