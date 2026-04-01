package a.entity.gus06.data.perform.iterate.previous;

import a.framework.*;
import java.util.List;
import java.io.File;
import java.util.Date;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151116";}


	private Service previousFile;
	private Service previousDay;


	public EntityImpl() throws Exception
	{
		previousFile = Outside.service(this,"gus06.file.perform.iterate.previous.listing0");
		previousDay = Outside.service(this,"gus06.time.date.previous.day");
	}


	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof String)
			return previous((String) obj);
			
		if(obj instanceof Integer)
			return previous((Integer) obj);
			
		if(obj instanceof File)
			return previous((File) obj);
			
		if(obj instanceof Date)
			return previous((Date) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private String previous(String v) throws Exception
	{
		if(v.length()!=1) throw new Exception("String not iterable");
		char c = v.charAt(0);
		c--;
		return ""+c;
	}
	
	private Integer previous(Integer v) throws Exception
	{
		return Integer.valueOf(v.intValue()-1);
	}
	
	private File previous(File v) throws Exception
	{
		return (File) previousFile.t(v);
	}
	
	private Date previous(Date d) throws Exception
	{
		return (Date) previousDay.t(d);
	}
}
