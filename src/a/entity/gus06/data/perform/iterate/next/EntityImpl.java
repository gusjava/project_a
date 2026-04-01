package a.entity.gus06.data.perform.iterate.next;

import a.framework.*;
import java.util.List;
import java.io.File;
import java.util.Iterator;
import java.util.Date;
import java.sql.ResultSet;
import java.io.BufferedReader;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150708";}


	private Service nextFile;
	private Service nextDay;


	public EntityImpl() throws Exception
	{
		nextFile = Outside.service(this,"gus06.file.perform.iterate.next.listing0");
		nextDay = Outside.service(this,"gus06.time.date.next.day");
	}


	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof String)
			return next((String) obj);
			
		if(obj instanceof Integer)
			return next((Integer) obj);
			
		if(obj instanceof File)
			return next((File) obj);
			
		if(obj instanceof Date)
			return next((Date) obj);
			
		if(obj instanceof Iterator)
			return next((Iterator) obj);
			
		if(obj instanceof G)
			return next((G) obj);
			
		if(obj instanceof ResultSet)
			return next((ResultSet) obj);
		
		if(obj instanceof BufferedReader)
			return next((BufferedReader) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private String next(String v) throws Exception
	{
		if(v.length()!=1) throw new Exception("String not iterable");
		char c = v.charAt(0);
		c++;
		return ""+c;
	}
	
	private Integer next(Integer v) throws Exception
	{
		return Integer.valueOf(v.intValue()+1);
	}
	
	private File next(File v) throws Exception
	{
		return (File) nextFile.t(v);
	}
	
	private Date next(Date d) throws Exception
	{
		return (Date) nextDay.t(d);
	}
	
	private Object next(Iterator it) throws Exception
	{
		return it.next();
	}
	
	private Object next(G g) throws Exception
	{
		return g.g();
	}
	
	private Object next(ResultSet rs) throws Exception
	{
		return rs.next();
	}
	
	private Object next(BufferedReader br) throws Exception
	{
		return br.readLine();
	}
}
