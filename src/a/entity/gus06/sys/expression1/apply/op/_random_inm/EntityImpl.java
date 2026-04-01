package a.entity.gus06.sys.expression1.apply.op._random_inm;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191021";}



	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof List) return rand((List) obj);
		if(obj instanceof int[]) return rand((int[]) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private int toInt(Object obj)
	{return ((Number) obj).intValue();}
	
	
	
	
	private Integer rand(int[] array) throws Exception
	{
		if(array.length!=2) throw new Exception("Wrong data number: "+array.length);
		int n = array[0];
		int m = array[1];
		return rand(n,m);
	}
	
	private Integer rand(List list) throws Exception
	{
		if(list.size()!=2) throw new Exception("Wrong data number: "+list.size());
		int n = toInt(list.get(0));
		int m = toInt(list.get(1));
		return rand(n,m);
	}
	
	private Integer rand(int n, int m)
	{return Integer.valueOf(random(m-n+1)+n);}
	
	private int random(int n)
	{return (int) (Math.random()*n);}
}
