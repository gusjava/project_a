package a.entity.gus06.sys.expression1.apply.op._months_fr;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160614";}

	public static final String T = "constant";


	private Service find;
	
	public EntityImpl() throws Exception
	{
		find = Outside.service(this,"gus06.data.time.months.name_fr");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		return find.g();
	}
}
