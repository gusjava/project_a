package a.entity.gus06.sys.expression1.apply.op._is_locked;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180308";}


	private Service check;
	
	public EntityImpl() throws Exception
	{
		check = Outside.service(this,"gus.x.file.filter.islocked");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return Boolean.FALSE;
		if(obj instanceof File) return Boolean.valueOf(check.f(obj));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
