package a.entity.gus06.sys.expression1.apply.op._co_letter;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160502";}

	private Service readText;
	private Service check;
	
	public EntityImpl() throws Exception
	{
		readText = Outside.service(this,"gus06.file.read.string.generic");
		check = Outside.service(this,"gus06.filter.string.haschar.letter");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return Boolean.FALSE;
		if(obj instanceof String) return Boolean.valueOf(check.f(obj));
		if(obj instanceof File) return Boolean.valueOf(check.f(readText.t(obj)));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
