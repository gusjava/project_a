package a.entity.gus06.sys.expression1.apply.op._git_open;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200116";}


	private Service buildFile;
	private Service perform;
	
	public EntityImpl() throws Exception
	{
		buildFile = Outside.service(this,"gus06.sys.expression1.file.build");
		perform = Outside.service(this,"gus06.sys.git1.builder");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		Map opMap = (Map) o[1];
		
		if(value==null) return null;
		if(value instanceof File) return perform.t(value);
		if(value instanceof String) return perform.t(file((String) value, opMap));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private File file(String value, Map opMap) throws Exception
	{return (File) buildFile.t(new Object[]{value, opMap});}
}