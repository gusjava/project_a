package a.entity.gus06.sys.expression1.apply.op._isfile;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151110";}

	private Service check;
	private Service buildFile;

	public EntityImpl() throws Exception
	{
		check = Outside.service(this,"gus06.file.isfile.casesensitive");
		buildFile = Outside.service(this,"gus06.sys.expression1.file.build");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		Map opMap = (Map) o[1];
		
		if(value==null) return Boolean.FALSE;
		if(!(value instanceof File)) return Boolean.FALSE;
			
		File file = file(value,opMap);
		return Boolean.valueOf(check.f(file));
	}
	
	private File file(Object value, Map opMap) throws Exception
	{
		if(value instanceof File) return (File) value;
		if(value instanceof String) return (File) buildFile.t(new Object[]{value, opMap});
		throw new Exception("Invalid data type: "+value.getClass().getName());
	}
}