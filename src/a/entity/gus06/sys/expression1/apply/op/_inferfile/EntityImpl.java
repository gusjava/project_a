package a.entity.gus06.sys.expression1.apply.op._inferfile;

import a.framework.*;
import java.util.Map;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180320";}


	private Service perform;
	private Service buildDir;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.data.perform.inferfile");
		buildDir = Outside.service(this,"gus06.sys.expression1.file.build");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		Map opMap = (Map) o[1];
		
		if(value==null) return null;
		if(value instanceof String) return perform.t(new Object[]{value,buildDir(opMap)});
		
		throw new Exception("Invalid data type: "+value.getClass().getName());
	}
	
	private File buildDir(Map opMap) throws Exception
	{return (File) buildDir.t(opMap);}
}
