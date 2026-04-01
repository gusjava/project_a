package a.entity.gus06.jdbc.connection.builder.get.objectarray;

import a.framework.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20260110";}

	private Service fromStringArray;
	private Service fromFile;

	public EntityImpl() throws Exception
	{
		fromStringArray = Outside.service(this,"gus06.jdbc.connection.builder.get.stringarray");
		fromFile = Outside.service(this,"gus06.jdbc.connection.builder.get.file");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] array = (Object[]) obj;
		
		Object src = get1(array,0);
		String user = (String) get0(array,1);
		String pwd = (String) get0(array,2);
		
		if(src instanceof String)
			return fromStringArray.t(new String[]{(String) src, user, pwd});
			
		if(src instanceof File)
			return fromFile.t(new Object[]{(File) src, user, pwd});
			
		throw new Exception("Unsupported src type: "+src.getClass().getName());
	}
	
	private Object get0(Object[] array, int index) throws Exception
	{
		if(array.length <= index) return null;
		return array[index];
	}
	
	private Object get1(Object[] array, int index) throws Exception
	{
		if(array.length <= index)throw new Exception("Element not found at index: "+index);
		return array[index];
	}
}
