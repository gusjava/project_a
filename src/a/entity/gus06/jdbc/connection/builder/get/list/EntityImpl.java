package a.entity.gus06.jdbc.connection.builder.get.list;

import a.framework.*;
import java.util.List;
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
		List list = (List) obj;
		
		Object src = get1(list,0);
		String user = (String) get0(list,1);
		String pwd = (String) get0(list,2);
		
		if(src instanceof String)
			return fromStringArray.t(new String[]{(String) src, user, pwd});
			
		if(src instanceof File)
			return fromFile.t(new Object[]{(File) src, user, pwd});
			
		throw new Exception("Unsupported src type: "+src.getClass().getName());
	}
	
	private Object get0(List list, int index) throws Exception
	{
		if(list.size() <= index) return null;
		return list.get(index);
	}
	
	private Object get1(List list, int index) throws Exception
	{
		if(list.size() <= index) throw new Exception("Element not found at index: "+index);
		return list.get(index);
	}
}
