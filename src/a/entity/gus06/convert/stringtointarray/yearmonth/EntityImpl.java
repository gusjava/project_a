package a.entity.gus06.convert.stringtointarray.yearmonth;

import a.framework.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191208";}


	private Service dataToIntArray;
	
	public EntityImpl() throws Exception
	{
		dataToIntArray = Outside.service(this,"gus06.time.date.get.yearmonth");
	}


	private SimpleDateFormat[] sdf7 = new SimpleDateFormat[]{
		
		new SimpleDateFormat("yyyy MM"),
		new SimpleDateFormat("MM yyyy"),
		new SimpleDateFormat("yyyy-MM"),
		new SimpleDateFormat("yyyy.MM"),
		new SimpleDateFormat("MM/yyyy")
	};
	
	private SimpleDateFormat[] sdf6 = new SimpleDateFormat[]{
		
		new SimpleDateFormat("yyyyMM"),
		new SimpleDateFormat("yyyy M"),
		new SimpleDateFormat("M yyyy"),
		new SimpleDateFormat("yyyy-M"),
		new SimpleDateFormat("yyyy.M"),
		new SimpleDateFormat("M/yyyy")
	};
	
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		s = s.replaceAll("[_ \t]+"," ");
		SimpleDateFormat[] sdf_ = findSDF(s);
		
		if(sdf_!=null)
		for(SimpleDateFormat sdf:sdf_)
		{
			Date d = parse(sdf,s);
			if(d!=null) return toArray(d);
		}
		throw new Exception("Unknown date syntax: "+s);
	}
	
	
	private Date parse(SimpleDateFormat sdf, String s)
	{
		try{return sdf.parse(s);}
		catch(Exception e) {return null;}
	}
	
	
	private SimpleDateFormat[] findSDF(String s)
	{
		switch(s.length()) {
			case 7:return sdf7;
			case 6:return sdf6;
			default:return null;
		}
	}
	
	
	private int[] toArray(Date date) throws Exception
	{return (int[]) dataToIntArray.t(date);}
}
