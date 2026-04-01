package a.entity.gus06.sys.expression1.apply.op._filesize;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160711";}


	private Service buildFile;

	public EntityImpl() throws Exception
	{
		buildFile = Outside.service(this,"gus06.sys.expression1.file.build");
	}



	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		Map opMap = (Map) o[1];
		
		if(value==null) return null;
		
		if(value instanceof String) return fileSize(file((String) value, opMap));
		if(value instanceof File) return fileSize((File) value);
		if(value instanceof File[]) return fileSize((File[]) value);
		
		throw new Exception("Invalid data type: "+value.getClass().getName());
	}
	
	
	
	private Long fileSize(File file)
	{
		if(!file.exists()) return null;
		return Long.valueOf(totalSize(file));
	}
	
	private Long fileSize(File[] files)
	{
		return Long.valueOf(totalSize(files));
	}
	
	
	
	
	
	private long totalSize(File path)
	{
		if(!path.exists()) return 0;
		if(path.isFile()) return path.length();
		
		long size = 0;
		File[] ff = path.listFiles();
		for(int i=0;i<ff.length;i++)
			size += totalSize(ff[i]);
		return size;
	}
	
	private long totalSize(File[] paths)
	{
		long size = 0;
		for(File path:paths)
			size += totalSize(path);
		return size;
	}
	
	private File file(String s, Map opMap) throws Exception
	{return (File) buildFile.t(new Object[]{s,opMap});}
}
