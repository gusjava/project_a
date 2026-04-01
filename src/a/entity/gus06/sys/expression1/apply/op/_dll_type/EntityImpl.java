package a.entity.gus06.sys.expression1.apply.op._dll_type;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180219";}


	private Service findType;
	private Service buildFile;


	public EntityImpl() throws Exception
	{
		findType = Outside.service(this,"gus06.env.windows.dll.findtype");
		buildFile = Outside.service(this,"gus06.sys.expression1.file.build");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		Map opMap = (Map) o[1];
		
		if(value==null) return null;
		
		if(value instanceof String) return findType(file((String) value, opMap));
		if(value instanceof File) return findType((File) value);
		
		throw new Exception("Invalid data type: "+value.getClass().getName());
	}
	
	
	private String findType(File file) throws Exception
	{return (String) findType.t(file);}
	
	private File file(String s, Map opMap) throws Exception
	{return (File) buildFile.t(new Object[]{s,opMap});}
}
