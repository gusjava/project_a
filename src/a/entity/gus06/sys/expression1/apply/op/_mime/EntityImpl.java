package a.entity.gus06.sys.expression1.apply.op._mime;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151110";}


	private Service buildMime;
	private Service buildFile;
	private Service nameToMime;
	
	public EntityImpl() throws Exception
	{
		buildMime = Outside.service(this,"gus06.file.mime.tika.detect.asstring");
		buildFile = Outside.service(this,"gus06.sys.expression1.file.build");
		nameToMime = Outside.service(this,"gus06.file.name.mimetype");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		Map opMap = (Map) o[1];
		
		if(value==null) return null;
		if(value instanceof File)
		{
			File file = (File) value;
			return file.isFile()?buildMime.t(file):null;
		}
		if(value instanceof String)
		{
			File file = file((String) value, opMap);
			if(file.isFile()) return buildMime.t(file);
			return nameToMime.t(value);
		}
		
		throw new Exception("Invalid data type: "+value.getClass().getName());
	}
	
	private File file(String s, Map opMap) throws Exception
	{return (File) buildFile.t(new Object[]{s,opMap});}
}
