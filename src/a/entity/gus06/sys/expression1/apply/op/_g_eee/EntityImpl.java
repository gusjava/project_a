package a.entity.gus06.sys.expression1.apply.op._g_eee;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180330";}

	
	private Service perform;
	private Service toArray;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.feature.op.sum.g.eee");
		toArray = Outside.service(this,"gus06.convert.listtogarray");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof G[]) return perform.t(obj);
		if(obj instanceof List) return perform.t(toArray.t(obj));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
