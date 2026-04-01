package a.entity.gus06.file.name.extract.date1;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250903";}

	private Service extract;
	private Service stringToDate;

	public EntityImpl() throws Exception
	{
		extract = Outside.service(this,"gus06.string.extract.date1.f");
		stringToDate = Outside.service(this,"gus06.convert.stringtodate");
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		String name = toName(obj);
		String s = (String) extract.t(name);
		if(s==null) return null;
		return stringToDate.t(s);
	}
	
	
	private String toName(Object obj) throws Exception
	{
		if(obj instanceof String) return (String) obj;
		if(obj instanceof File) return ((File) obj).getName();
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}