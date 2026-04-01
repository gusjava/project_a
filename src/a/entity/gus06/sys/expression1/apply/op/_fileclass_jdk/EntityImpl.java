package a.entity.gus06.sys.expression1.apply.op._fileclass_jdk;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161108";}


	private Service find;
	private Service buildFile;


	public EntityImpl() throws Exception
	{
		find = Outside.service(this,"gus06.file.class1.jdkversion");
		buildFile = Outside.service(this,"gus06.sys.expression1.file.build");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		Map opMap = (Map) o[1];
		
		if(value==null) return null;
		
		if(value instanceof File) return find.t(value);
		if(value instanceof String) return find.t(file((String) value, opMap));
		
		throw new Exception("Invalid data type: "+value.getClass().getName());
	}
	
	private File file(String s, Map opMap) throws Exception
	{return (File) buildFile.t(new Object[]{s,opMap});}
}
