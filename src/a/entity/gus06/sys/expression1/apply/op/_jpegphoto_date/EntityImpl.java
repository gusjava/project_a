package a.entity.gus06.sys.expression1.apply.op._jpegphoto_date;

import a.framework.*;
import java.io.File;
import java.util.Date;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180218";}


	private Service buildFile;
	private Service extract;

	public EntityImpl() throws Exception
	{
		buildFile = Outside.service(this,"gus06.sys.expression1.file.build");
		extract = Outside.service(this,"gus06.file.image.extraction.jpegphoto.originaltime");
	}



	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		Map opMap = (Map) o[1];
		
		if(value==null) return null;
		
		if(value instanceof String) return extract(file((String) value, opMap));
		if(value instanceof File) return extract((File) value);
		
		throw new Exception("Invalid data type: "+value.getClass().getName());
	}
	
	
	
	private Object extract(File file) throws Exception
	{
		if(!file.exists()) return null;
		return extract.t(file);
	}
	
	private File file(String s, Map opMap) throws Exception
	{return (File) buildFile.t(new Object[]{s,opMap});}
}
