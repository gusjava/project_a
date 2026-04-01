package a.entity.gus06.math.matrixdim.build;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180110";}


	public Object t(Object obj) throws Exception
	{
		int[] n = toIntArray(obj);
		if(n[0]<0) throw new Exception("Invalid dim value: "+n[0]);
		if(n[1]<0) throw new Exception("Invalid dim value: "+n[1]);
		return n;
	}
	
	
	
	private int[] toIntArray(Object obj) throws Exception
	{
		if(obj instanceof int[])
		{
			int[] s = (int[]) obj;
			if(s.length!=2) throw new Exception("Wrong data number: "+s.length);
			return s;
		}
		if(obj instanceof Object[])
		{
			Object[] s = (Object[]) obj;
			if(s.length!=2) throw new Exception("Wrong data number: "+s.length);
			return new int[]{toInt(s[0]),toInt(s[1])};
		}
		if(obj instanceof List)
		{
			List l = (List) obj;
			if(l.size()!=2) throw new Exception("Wrong data number: "+l.size());
			return new int[]{toInt(l.get(0)),toInt(l.get(1))};
		}
		if(obj instanceof Integer)
		{
			int n = ((Integer)obj).intValue();
			return new int[]{n,n};
		}
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private int toInt(Object obj)
	{return ((Integer) obj).intValue();}
}
