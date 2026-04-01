package a.entity.gus06.sys.expression1.apply.op._torectangle;

import a.framework.*;
import java.awt.Rectangle;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170306";}


	private Service find;
	
	public EntityImpl() throws Exception
	{
		find = Outside.service(this,"gus06.find.rectangle");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof Rectangle) return obj;
		if(obj instanceof String) return find.t(obj);
		if(obj instanceof int[]) return find.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
