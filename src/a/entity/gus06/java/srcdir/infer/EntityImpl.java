package a.entity.gus06.java.srcdir.infer;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20251219";}
	
	public static final String SEP = "/";
	
	public static final String DELIM1 = "/src/main/java/";
	public static final String DELIM2 = "/src/test/java/";
	public static final String DELIM3 = "/src/";
	public static final String DELIM4 = "/src-java/";
	public static final String DELIM5 = "/java/";
	public static final String DELIM6 = "/sources/";
	
	public Object t(Object obj) throws Exception
	{
		File file = toFile(obj);
		if(!file.exists()) throw new Exception("Path does not exist: "+file);
		if(file.isFile()) file = file.getParentFile();
		
		String path = path(file)+SEP;
		String rootPath = inferPath(path);
		
		return rootPath!=null ? new File(rootPath) : null;
	}
	
	private String inferPath(String path)
	{
		if(path.contains(DELIM1)) return firstPart(path,DELIM1);
		if(path.contains(DELIM2)) return firstPart(path,DELIM2);
		if(path.contains(DELIM3)) return firstPart(path,DELIM3);
		if(path.contains(DELIM4)) return firstPart(path,DELIM4);
		if(path.contains(DELIM5)) return firstPart(path,DELIM5);
		if(path.contains(DELIM6)) return firstPart(path,DELIM6);
		return null;
	}
	
	private String firstPart(String path, String delim)
	{
		int idx = path.indexOf(delim);
		return idx<0 ? null : path.substring(0,idx)+delim;
	}
	
	
	private File toFile(Object obj) throws Exception
	{
		if(obj instanceof File) return (File) obj;
		if(obj instanceof String) return new File((String) obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private String path(File file)
	{return file.getAbsolutePath().replace(File.separator, SEP);}
}
