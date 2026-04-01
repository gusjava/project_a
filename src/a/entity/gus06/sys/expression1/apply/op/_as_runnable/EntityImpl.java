package a.entity.gus06.sys.expression1.apply.op._as_runnable;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20240327";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof Runnable) return new Runnable1((Runnable) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class Runnable1 implements Runnable
	{
		private Runnable r;
		public Runnable1(Runnable r){this.r = r;}
		
		public void run()
		{r.run();}
	}
}