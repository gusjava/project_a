package a.entity.gus06.sys.expression1.apply.op._katakana_map;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250620";}

	public static final String T = "constant";


	private Service build;
	
	public EntityImpl() throws Exception
	{
		build = Outside.service(this,"gus06.string.transform.japanese.katakana.builder");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		return build.g();
	}
}