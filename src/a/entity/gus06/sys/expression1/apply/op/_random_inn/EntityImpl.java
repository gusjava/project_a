package a.entity.gus06.sys.expression1.apply.op._random_inn;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191021";}



	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof Integer)
		{
			int n = toInt(obj);
			return Integer.valueOf(random(n+1)*2-n);
		}
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private int toInt(Object obj)
	{return ((Number) obj).intValue();}
	
	
	private int random(int n)
	{return (int) (Math.random()*n);}
}
