package a.entity.gus06.jdbc.connection.builder.get.stringarray;

import a.framework.*;
import java.sql.DriverManager;
import java.sql.Connection;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20260110";}

	private Service completeUrl;

	public EntityImpl() throws Exception
	{
		completeUrl = Outside.service(this,"gus06.jdbc.connection.builder.completeurl");
	}
	
	public Object t(Object obj) throws Exception
	{
		String[] array = (String[]) obj;
		
		String url = get1(array,0);
		String user = get0(array,1);
		String pwd = get0(array,2);
		
		return getConnection(url,user,pwd);
	}
	
	private Connection getConnection(String url, String user, String pwd) throws Exception
	{
		try
		{
			url = (String) completeUrl.t(url);
			
			if(user==null || user.equals(""))
				return DriverManager.getConnection(url);
			return DriverManager.getConnection(url,user,pwd);
		}
		catch(Exception e)
		{
			String message = "JDBC connection failed for url="+url+" & user="+user;
			throw new Exception(message,e);
		}
	}
	
	private String get0(String[] array, int index) throws Exception
	{
		if(array.length <= index) return null;
		return array[index];
	}
	
	private String get1(String[] array, int index) throws Exception
	{
		if(array.length <= index) throw new Exception("Element not found at index: "+index);
		return array[index];
	}
}