package a.entity.gus06.filter.date.build.after.now.remove.days;

import a.framework.*;
import java.util.Date;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191106";}
	
	
	private Service toDate;
	private Service removeDaysFromNow;
	
	public EntityImpl() throws Exception
	{
		toDate = Outside.service(this,"gus06.find.date");
		removeDaysFromNow = Outside.service(this,"gus06.time.date.remove.days.fromnow");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Integer nb = (Integer) obj;
		return new Filter(nb);
	}
	
	
	private class Filter implements F
	{
		private Integer nb;
		public Filter(Integer nb)
		{this.nb = nb;}
		
		public boolean f(Object obj) throws Exception
		{
			if(obj==null) return false;
			Date d = (Date) toDate.t(obj);
			Date d0 = (Date) removeDaysFromNow.t(nb);
			return d.after(d0);
		}
	}
}
