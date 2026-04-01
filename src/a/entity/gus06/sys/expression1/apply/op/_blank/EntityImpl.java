package a.entity.gus06.sys.expression1.apply.op._blank;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160108";}

	private Service readText;
	
	public EntityImpl() throws Exception
	{
		readText = Outside.service(this,"gus06.file.read.string.generic");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return Boolean.TRUE;
		if(obj instanceof String) return Boolean.valueOf(blank((String) obj));
		if(obj instanceof File) return Boolean.valueOf(blank((String) readText.t(obj)));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private boolean blank(String s)
	{return s.trim().equals("");}
}
