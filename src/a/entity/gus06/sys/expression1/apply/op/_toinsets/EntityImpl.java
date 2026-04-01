package a.entity.gus06.sys.expression1.apply.op._toinsets;

import a.framework.*;
import java.util.List;
import java.awt.Insets;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160907";}


	private Service perform;
	private Service listToArray;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.find.insets");
		listToArray = Outside.service(this,"gus06.convert.listtointarray.strict");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof Insets) return obj;
		
		if(obj instanceof Integer) return perform.t(obj);
		if(obj instanceof String) return perform.t(obj);
		if(obj instanceof int[]) return perform.t(obj);
		if(obj instanceof List) return perform.t(toArray(obj));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private Object toArray(Object obj) throws Exception
	{
		Object r = listToArray.t(obj);
		if(r==null) throw new Exception("Invalid list: "+obj);
		return r;
	}
}
