package a.entity.gus06.y.api2.sqlite.cx.build;

import java.io.File;
import a.framework.*;
import java.sql.DriverManager;

public class EntityImpl implements Entity, T {
	
	public String creationDate() {return "20250723";}
	
	public Object t(Object obj) throws Exception
	{
		String url = toUrl(obj);
		return DriverManager.getConnection(url);
	}
	
	private String toUrl(Object obj) throws Exception
	{
		if(obj instanceof String) return (String) obj;
		if(obj instanceof File) return fileToUrl((File) obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private String fileToUrl(File file)
	{
		file.getParentFile().mkdirs();
		String path = file.getAbsolutePath().replace("\\", "/");
//		path = path.replace(" ", "%20");
		return "jdbc:sqlite:" + path;
	}
}