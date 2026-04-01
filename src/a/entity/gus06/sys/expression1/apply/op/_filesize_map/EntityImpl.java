package a.entity.gus06.sys.expression1.apply.op._filesize_map;

import a.framework.*;
import java.util.Map;
import java.io.File;
import java.util.Set;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161116";}


	private Service perform;
	private Service buildFile;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.dir.listing.dirtomap.file_size");
		buildFile = Outside.service(this,"gus06.sys.expression1.file.build");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		Map opMap = (Map) o[1];
		
		if(value==null) return null;
		
		if(value instanceof String) return perform.t(file((String) value, opMap));
		if(value instanceof File) return perform.t((File) value);
		
		throw new Exception("Invalid data type: "+value.getClass().getName());
	}
	
	private File file(String s, Map opMap) throws Exception
	{return (File) buildFile.t(new Object[]{s,opMap});}
}
