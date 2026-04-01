package a.entity.gus06.sys.expression1.apply.op._vec_centroid;

import a.framework.*;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160806";}


	private Service perform;
	
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.math.tabdouble.list.centroid");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof List) return perform.t(obj);
		if(obj instanceof Set) return perform.t(new ArrayList((Set) obj));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
