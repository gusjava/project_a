package a.entity.gus06.sys.expression1.apply.op._parent_l4;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160325";}
	
	public static final int LEVEL = 4;
	
	
	private Service perform;
	
	public EntityImpl() throws Exception
	{perform = Outside.service(this,"gus06.data.perform.parent");}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof File) return parent(obj,LEVEL);
		if(obj instanceof Class) return parent(obj,LEVEL);
		if(obj instanceof Map) return parent(obj,LEVEL);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private Object parent(Object obj, int level) throws Exception
	{
		for(int i=0;i<level;i++) if(obj!=null)
		obj = perform.t(obj);
		return obj;
	}
}
