package a.entity.gus06.sys.parser3.resolver1.op.binary.sup1;

import a.framework.*;
import java.util.List;
import java.util.Date;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151029";}


	private Service stringToDate;
	
	public EntityImpl() throws Exception
	{
		stringToDate = Outside.service(this,"gus06.convert.stringtodate");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List cut = (List) o[0];
		T t = (T) o[1];
		
		if(cut.size()!=2) throw new Exception("Invalid split for equals operation: "+cut.size());
		
		Object o1 = t.t(cut.get(0));
		Object o2 = t.t(cut.get(1));
		
		if(o1==null) return Boolean.FALSE;
		if(o2==null) return Boolean.FALSE;
		
		if(o1 instanceof Number && o2 instanceof Number)
			return Boolean.valueOf(numberToDouble(o1) >= numberToDouble(o2));
		
		if(o1 instanceof int[] && o2 instanceof int[])
			return Boolean.valueOf(compareArray((int[]) o1, (int[]) o2));
		
		if(o1 instanceof long[] && o2 instanceof long[])
			return Boolean.valueOf(compareArray((long[]) o1, (long[]) o2));
		
		if(o1 instanceof double[] && o2 instanceof double[])
			return Boolean.valueOf(compareArray((double[]) o1, (double[]) o2));
		
		if(o1 instanceof Date && o2 instanceof Date)
			return Boolean.valueOf(dateToLong(o1) >= dateToLong(o2));
		
		if(o1 instanceof Date && o2 instanceof String)
			return Boolean.valueOf(dateToLong(o1) >= strDateToLong(o2));
		
		if(o1 instanceof String && o2 instanceof Date)
			return Boolean.valueOf(strDateToLong(o1) >= dateToLong(o2));
		
		if(o1 instanceof String && o2 instanceof String)
			return Boolean.valueOf(((String) o1).compareTo((String) o2) >= 0);
		
		return Boolean.FALSE;
	}
	
	
	private double numberToDouble(Object obj)
	{return ((Number) obj).doubleValue();}
	
	private long dateToLong(Object obj)
	{return ((Date) obj).getTime();}
	
	private long strDateToLong(Object obj) throws Exception
	{return ((Date) stringToDate.t(obj)).getTime();}
	
	
	
	private boolean compareArray(int[] t1, int[] t2)
	{
		int nb1 = t1.length;
		int nb2 = t2.length;
		int nb = Math.max(nb1, nb2);
		for(int i=0;i<nb;i++)
		{
			int v1 = i<nb1 ? t1[i] : 0;
			int v2 = i<nb2 ? t2[i] : 0;
			if(v1!=v2) return v1>v2;
		}
		return true;
	}
	
	private boolean compareArray(long[] t1, long[] t2)
	{
		int nb1 = t1.length;
		int nb2 = t2.length;
		int nb = Math.max(nb1, nb2);
		for(int i=0;i<nb;i++)
		{
			long v1 = i<nb1 ? t1[i] : 0;
			long v2 = i<nb2 ? t2[i] : 0;
			if(v1!=v2) return v1>v2;
		}
		return true;
	}
	
	private boolean compareArray(double[] t1, double[] t2)
	{
		int nb1 = t1.length;
		int nb2 = t2.length;
		int nb = Math.max(nb1, nb2);
		for(int i=0;i<nb;i++)
		{
			double v1 = i<nb1 ? t1[i] : 0;
			double v2 = i<nb2 ? t2[i] : 0;
			if(v1!=v2) return v1>v2;
		}
		return true;
	}
}