package a.entity.gus06.data.compare.o2;

import a.framework.*;
import java.util.Date;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161215";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		return Integer.valueOf(compare(o[0],o[1]));
	}
	
	private int compare(Object o1, Object o2) throws Exception
	{
		if(o1==null) throw new Exception("Invalid null value for o1");
		if(o2==null) throw new Exception("Invalid null value for o2");
		
		if(o1 instanceof Number && o2 instanceof Number)
		{
			double d1 = numberToDouble(o1);
			double d2 = numberToDouble(o2);
			return d1==d2 ? 0 : d1>d2 ? 1 : -1;
		}
		if(o1 instanceof Date && o2 instanceof Date)
		{
			long d1 = dateToLong(o1);
			long d2 = dateToLong(o2);
			return d1==d2 ? 0 : d1>d2 ? 1 : -1;
		}
		if(o1 instanceof String && o2 instanceof String)
		{
			String s1 = (String) o1;
			String s2 = (String) o2;
			return s1.compareTo(s2);
		}
		throw new Exception("Invalid data types: "+o1.getClass().getName()+" and "+o2.getClass().getName());
	}
	
	
	private double numberToDouble(Object obj)
	{return ((Number) obj).doubleValue();}
	
	private long dateToLong(Object obj)
	{return ((Date) obj).getTime();}
}
