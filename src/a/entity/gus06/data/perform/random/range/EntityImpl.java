package a.entity.gus06.data.perform.random.range;

import a.framework.*;
import java.util.Date;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220618";}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof double[]) return Double.valueOf(random((double[]) obj));
		if(obj instanceof long[]) return Long.valueOf(random((long[]) obj));
		if(obj instanceof int[]) return Integer.valueOf(random((int[]) obj));
		if(obj instanceof Date[]) return new Date(random((Date[]) obj));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private double random(double[] n) throws Exception
	{
		if(n.length!=2) throw new Exception("Invalid data length: "+n.length);
		return n[0]+random(n[1]-n[0]);
	}
	
	private long random(long[] n) throws Exception
	{
		if(n.length!=2) throw new Exception("Invalid data length: "+n.length);
		return n[0]+random(n[1]-n[0]);
	}
	
	private int random(int[] n) throws Exception
	{
		if(n.length!=2) throw new Exception("Invalid data length: "+n.length);
		return n[0]+random(n[1]-n[0]);
	}
	
	private long random(Date[] n) throws Exception
	{
		if(n.length!=2) throw new Exception("Invalid data length: "+n.length);
		long t1 = n[0].getTime();
		long t2 = n[1].getTime();
		return t1+random(t2-t1);
	}
	
	
	
	private double random(double n)
	{return Math.random()*n;}
	
	private int random(int n)
	{return (int) (Math.random()*n);}
	
	private long random(long n)
	{return (long) (Math.random()*n);}
}