package a.entity.gus06.string.transform.format.timestamp.age;

import a.framework.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151015";}

	private SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
	private SimpleDateFormat MMdd = new SimpleDateFormat("MMdd");
	private SimpleDateFormat yyyy = new SimpleDateFormat("yyyy");
	
	
	private Service stringToDate;
	
	private int thisYear;


	public EntityImpl() throws Exception
	{
		stringToDate = Outside.service(this,"gus06.convert.stringtodate");
		thisYear = int_(yyyy(new Date()));
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Date birthDate = (Date) stringToDate.t(obj);
		if(birthDate.after(new Date()))
			throw new Exception("Futurist birth date: "+birthDate);
		
		int birthYear = int_(yyyy(birthDate));
		int thisAge = thisYear - birthYear;
		
		String birthday0 = MMdd(birthDate);
		Date thisBirthDate = yyyyMMdd.parse(thisYear+birthday0);
		if(thisBirthDate.after(new Date())) thisAge--;
		
		return ""+thisAge;
	}
	
	
	
	private String yyyy(Date d) throws Exception
	{return yyyy.format(d);}
	
	private String MMdd(Date d) throws Exception
	{return MMdd.format(d);}
	
	private int int_(String s)
	{return Integer.parseInt(s);}
}
