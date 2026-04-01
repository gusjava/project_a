package a.entity.gus06.jdbc.connection.builder.get.file;

import a.framework.*;
import java.util.Map;
import java.sql.DriverManager;
import java.sql.Connection;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20260110";}

	private Service buildH2;
	private Service buildSqlite;

	public EntityImpl() throws Exception
	{
		buildH2 = Outside.service(this,"gus06.y.api2.h2.cx.build");
		buildSqlite = Outside.service(this,"gus06.y.api2.sqlite.cx.build");
	}
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof Object[]) return handleArray((Object[]) obj);
		if(obj instanceof File) return handleFile((File) obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private Object handleArray(Object[] o) throws Exception
	{
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		File file = (File) o[0];
		String user = (String) o[1];
		String pwd = (String) o[2];
		
		if(file.isDirectory()) throw new Exception("Invalid file: "+file);
		String name = file.getName().toLowerCase();
		
		if(name.endsWith(".mv.db")) return buildH2.t(new Object[]{file,user,pwd});
		if(name.endsWith(".sqlite")) throw new Exception("User/pwd not supported for sqlite file");
		
		throw new Exception("Unsupported file type: "+file.getAbsolutePath());
	}
	
	private Object handleFile(File file) throws Exception
	{
		if(file.isDirectory()) throw new Exception("Invalid file: "+file);
		String name = file.getName().toLowerCase();
		
		if(name.endsWith(".mv.db")) return buildH2.t(file);
		if(name.endsWith(".sqlite")) return buildSqlite.t(file);
		
		throw new Exception("Unsupported file type: "+file.getAbsolutePath());
	}
}
