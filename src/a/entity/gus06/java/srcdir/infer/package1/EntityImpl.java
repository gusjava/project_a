package a.entity.gus06.java.srcdir.infer.package1;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20251223";}
	
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
		return inferPackage(file);
	}
	
	private String inferPackage(File file)
	{
		String path = path(file)+SEP;
		
		String delim = findDelim(path);
		if(delim==null) return null;
		
		int idx = path.indexOf(delim);
		int len = idx + delim.length();
		
		String relPath = path.substring(len);
		String p = relPath.replace(SEP,".");
		
		if(p.startsWith(".")) p = p.substring(1);
		if(p.endsWith(".")) p = p.substring(0,p.length()-1);
		return p;
	}

	private String findDelim(String path)
	{
		if(path.contains(DELIM1)) return DELIM1;
		if(path.contains(DELIM2)) return DELIM2;
		if(path.contains(DELIM3)) return DELIM3;
		if(path.contains(DELIM4)) return DELIM4;
		if(path.contains(DELIM5)) return DELIM5;
		if(path.contains(DELIM6)) return DELIM6;
		return null;
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
