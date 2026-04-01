package a.entity.gus06.time.execute.waitfor.min;

import a.framework.*;

public class EntityImpl implements Entity, T, P {

	public String creationDate() {return "20170819";}
	
	public static final long L = 60000L;
	
	
	public Object t(Object obj) throws Exception
	{return new E1(toLong(obj));}
	
	private long toLong(Object obj)
	{return Long.parseLong(""+obj);}
	
	
	public void p(Object obj) throws Exception
	{((E) t(obj)).e();}
	
	
	
	private class E1 implements E
	{
		private long d;
		public E1(long d){this.d = d;}
		
		public void e() throws Exception
		{
			long t0 = System.currentTimeMillis();
			long t1 = t0+d*L;
			
			while(System.currentTimeMillis()<t1)
			{sleep_1();}
		}
	}
	
	private void sleep_1()
	{
		try{Thread.sleep(1);}
		catch(Exception e)
		{Outside.err(this,"sleep_1()",e);}
	}
}