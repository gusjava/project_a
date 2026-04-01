package a.entity.gus06.time.date.same.month;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20231114";}

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");

	private Service findDate;
	
	public EntityImpl() throws Exception
	{
		findDate = Outside.service(this,"gus06.find.date");
	}
	
	public boolean f(Object obj) throws Exception
	{
		if(obj instanceof List) return handleList((List) obj);
		if(obj instanceof Object[]) return handleArray((Object[]) obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private boolean handleList(List list) throws Exception
	{
		if(list.size()<2) return true;
		String t0 = sdf.format(findDate(list.get(0)));
		for(int i=1;i<list.size();i++)
		{
			String t = sdf.format(findDate(list.get(i)));
			if(!t0.equals(t)) return false;
		}
		return true;
	}
	
	private boolean handleArray(Object[] array) throws Exception
	{
		if(array.length<2) return true;
		String t0 = sdf.format(findDate(array[0]));
		for(int i=1;i<array.length;i++)
		{
			String t = sdf.format(findDate(array[i]));
			if(!t0.equals(t)) return false;
		}
		return true;
	}
	
	private Date findDate(Object obj) throws Exception
	{return (Date) findDate.t(obj);}
}