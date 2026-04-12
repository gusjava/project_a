package a.entity.gus.y.find1.file;

import a.framework.*;
import java.io.File;
import java.nio.file.Path;
import java.util.List;
import java.io.InputStream;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20240618";}
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		if(obj instanceof File) return obj;
		if(obj instanceof String) return new File((String) obj);
		if(obj instanceof Path) return ((Path) obj).toFile();
		if(obj instanceof List) return uniqueFile((List) obj);
		if(obj instanceof File[]) return uniqueFile((File[]) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private File uniqueFile(List list) throws Exception
	{
		if(list.size()!=1) throw new Exception("Invalid list size for file conversion: "+list.size());
		return (File) list.get(0);
	}
	
	private File uniqueFile(File[] ff) throws Exception
	{
		if(ff.length!=1) throw new Exception("Invalid array size for file conversion: "+ff.length);
		return ff[0];
	}
}
