package a.entity.gus06.sys.expression1.apply.op._jre_bin;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170223";}

	public static final String T = "constant";


	private Service find;
	
	public EntityImpl() throws Exception
	{
		find = Outside.service(this,"gus06.java.dir.bin");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		return find.g();
	}
}
