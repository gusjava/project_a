package a.entity.gus06.sys.expression1.apply.op._read_prop_vault;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231026";}


	private Service readProperties;
	
	public EntityImpl() throws Exception
	{
		readProperties = Outside.service(this,"gus06.sys.vault2.read.file");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof File) return readProperties.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}