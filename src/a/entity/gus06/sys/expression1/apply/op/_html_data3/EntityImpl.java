package a.entity.gus06.sys.expression1.apply.op._html_data3;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190702";}


	private Service readText;
	private Service perform;
	
	public EntityImpl() throws Exception
	{
		readText = Outside.service(this,"gus06.file.read.string.generic");
		perform = Outside.service(this,"gus06.string.extract.html.data3");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof String) return perform.t(obj);
		if(obj instanceof File) return perform.t(readText.t(obj));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}