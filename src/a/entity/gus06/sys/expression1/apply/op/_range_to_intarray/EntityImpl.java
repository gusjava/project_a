package a.entity.gus06.sys.expression1.apply.op._range_to_intarray;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160705";}



	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof int[]) return buildArray((int[]) obj);
		if(obj instanceof List) return buildArray((List) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private int[] buildArray(int[] range) throws Exception
	{
		int n = range.length;
		if(n>3) throw new Exception("Invalid range info number: "+n);
		
		int count = n>0 ? range[0] : 0;
		int offset = n>1 ? range[1] : 0;
		int diff = n>2 ? range[2] : 1;
		
		int[] r = new int[count];
		for(int i=0;i<count;i++) r[i] = offset + diff*i;
		return r;
	}
	
	private int[] buildArray(List range) throws Exception
	{
		int n = range.size();
		if(n>3) throw new Exception("Invalid range info number: "+n);
		
		int count = n>0 ? toInt(range.get(0)) : 0;
		int offset = n>1 ? toInt(range.get(1)) : 0;
		int diff = n>2 ? toInt(range.get(2)) : 1;
		
		int[] r = new int[count];
		for(int i=0;i<count;i++) r[i] = offset + diff*i;
		return r;
	}
	
	private int toInt(Object obj) throws Exception
	{
		if(obj instanceof Number) return ((Number) obj).intValue();
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
