package a.entity.gus06.sys.expression1.apply.op._nco_hiragana;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160817";}

	private Service readText;
	private Service check;
	
	public EntityImpl() throws Exception
	{
		readText = Outside.service(this,"gus06.file.read.string.generic");
		check = Outside.service(this,"gus06.filter.string.haschar.japanese.hiragana");
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
	
	private boolean matches(String s) throws Exception
	{
		return !check.f(s);
	}
}
