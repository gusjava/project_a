package a.entity.gus06.sys.expression1.apply.op._gen_uuid;

import a.framework.*;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20210610";}

	public static final String T = "constant";


	private Service randomUUID;
	
	public EntityImpl() throws Exception
	{
		randomUUID = Outside.service(this,"gus06.data.generate.string.random.uuid");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		return randomUUID.g();
	}
}