package a.entity.gus06.filter.date.build.before;

import a.framework.*;
import java.util.Date;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191106";}
	
	
	private Service toDate;
	
	public EntityImpl() throws Exception
	{
		toDate = Outside.service(this,"gus06.find.date");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Date date = (Date) obj;
		return new Filter(date);
	}
	
	
	private class Filter implements F
	{
		private Date date;
		public Filter(Date date)
		{this.date = date;}
		
		public boolean f(Object obj) throws Exception
		{
			if(obj==null) return false;
			Date d = (Date) toDate.t(obj);
			return d.before(date);
		}
	}
}
