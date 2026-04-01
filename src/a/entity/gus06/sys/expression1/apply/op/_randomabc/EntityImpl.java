package a.entity.gus06.sys.expression1.apply.op._randomabc;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160730";}

	public static final String T = "constant";

	public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";


	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		int index = random(52);
		return ""+ALPHABET.charAt(index);
	}
	
	
	private int random(int n)
	{return (int) (Math.random()*n);}
}
