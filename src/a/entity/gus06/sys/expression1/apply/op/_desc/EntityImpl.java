package a.entity.gus06.sys.expression1.apply.op._desc;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151122";}


	private Service desc;


	public EntityImpl() throws Exception
	{
		desc = Outside.service(this,"gus06.tostring.desc");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		return desc.t(obj);
	}
}
