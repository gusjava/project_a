package a.entity.gus06.sys.expression1.apply.op._e_beeps;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201216";}


	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.beep.multibeep");
	}


	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof Number) return new E1((Number) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class E1 implements E
	{
		public Number nb;
		public E1(Number nb)
		{this.nb = nb;}
		
		public void e() throws Exception
		{perform.p(nb);}
	}
}