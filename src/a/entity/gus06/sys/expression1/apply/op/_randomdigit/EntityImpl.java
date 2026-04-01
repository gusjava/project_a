package a.entity.gus06.sys.expression1.apply.op._randomdigit;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161215";}

	public static final String T = "constant";


	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		return Integer.valueOf(random(10));
	}
	
	
	private int random(int n)
	{return (int) (Math.random()*n);}
}
