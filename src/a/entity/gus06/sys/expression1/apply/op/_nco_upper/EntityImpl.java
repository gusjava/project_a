package a.entity.gus06.sys.expression1.apply.op._nco_upper;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160502";}

	private Service readText;
	private Service check;
	
	public EntityImpl() throws Exception
	{
		readText = Outside.service(this,"gus06.file.read.string.generic");
		check = Outside.service(this,"gus06.filter.string.haschar.uppercase");
	}


	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return Boolean.TRUE;
		if(obj instanceof String) return Boolean.valueOf(matches((String) obj));
		if(obj instanceof File) return Boolean.valueOf(matches((String) readText.t(obj)));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	public boolean matches(String s) throws Exception
	{
		return !check.f(s);
	}
}
