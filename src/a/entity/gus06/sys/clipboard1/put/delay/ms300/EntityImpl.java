package a.entity.gus06.sys.clipboard1.put.delay.ms300;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20180226";}
	
	public static final Long LAPSE = 300L;


	private Service clipboard;
	private Service delay;


	public EntityImpl() throws Exception
	{
		clipboard = Outside.service(this,"gus06.clipboard.access");
		delay = Outside.service(this,"gus06.time.execute.delay.ms");
	}
	
	
	public void p(Object obj) throws Exception
	{
		E1 e = new E1(obj);
		delay.p(new Object[]{e,LAPSE});
	}
	
	
	private class E1 implements E
	{
		private Object data;
		public E1(Object data)
		
		{this.data = data;}
		
		public void e() throws Exception
		{clipboard.p(data);}
	}
}
