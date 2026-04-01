package a.entity.gus06.sys.expression1.apply.op._parsejava_propdata;

import a.framework.*;
import java.io.File;
import java.io.InputStream;
import java.io.Reader;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220930";}

	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.sys.javaparser1.extract.prop.data");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof String) return perform.t(obj);
		if(obj instanceof File) return perform.t(obj);
		if(obj instanceof InputStream) return perform.t(obj);
		if(obj instanceof Reader) return perform.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}