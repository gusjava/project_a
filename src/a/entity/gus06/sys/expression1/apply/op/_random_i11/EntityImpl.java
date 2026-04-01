package a.entity.gus06.sys.expression1.apply.op._random_i11;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191021";}

	public static final String T = "constant";
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		return Integer.valueOf(random(2)*2-1);
	}
	
	private int random(int n)
	{return (int) (Math.random()*n);}
}
