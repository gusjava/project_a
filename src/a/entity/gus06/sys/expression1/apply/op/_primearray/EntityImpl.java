package a.entity.gus06.sys.expression1.apply.op._primearray;

import a.framework.*;
import java.awt.Color;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20171118";}


	private Service find;

	public static final String T = "constant";
	
	public EntityImpl() throws Exception
	{
		find = Outside.service(this,"gus06.math.prime.array.until50000");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		return find.g();
	}
}
