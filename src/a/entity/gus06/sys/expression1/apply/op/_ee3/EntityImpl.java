package a.entity.gus06.sys.expression1.apply.op._ee3;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160924";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		if(obj==null) return null;
		
		if(obj instanceof E) return new EE((E) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class EE implements E, Runnable
	{
		private E e;
		
		public EE(E e) {this.e = e;}
		
		public void e() throws Exception
		{
			Thread t = new Thread(this,"THREAD_"+EntityImpl.class.getName());
			t.start();
		}
		
		public void run()
		{execute(e);}
	}
	
	
	
	private void execute(E execute)
	{
		try{execute.e();}
		catch(Exception e)
		{Outside.err(this,"execute(E)",e);}
	}

}