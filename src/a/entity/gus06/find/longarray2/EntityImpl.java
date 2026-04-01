package a.entity.gus06.find.longarray2;

import a.framework.*;
import java.util.*;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180113";}


	
	public EntityImpl() throws Exception
	{
	}

	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		if(obj instanceof long[][]) return obj;
		if(obj instanceof int[][]) return handle((int[][]) obj);
		if(obj instanceof double[][]) return handle((double[][]) obj);
		if(obj instanceof float[][]) return handle((float[][]) obj);
		if(obj instanceof Object[][]) return handle((Object[][]) obj);
		
		if(obj instanceof int[]) return handle((int[]) obj);
		if(obj instanceof long[]) return handle((long[]) obj);
		if(obj instanceof double[]) return handle((double[]) obj);
		if(obj instanceof float[]) return handle((float[]) obj);
		if(obj instanceof Object[]) return handle((Object[]) obj);
		
		if(obj instanceof Integer) return handle((Integer) obj);
		if(obj instanceof Long) return handle((Long) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private long[][] handle(int[][] t)
	{
		int nb1 = t.length;
		int nb2 = nb1>0 ? t[0].length : 0;
		long[][] n = new long[nb1][nb2];
		
		for(int i=0;i<nb1;i++)
		for(int j=0;j<nb2;j++)
		n[i][j] = (long) t[i][j];
		
		return n;
	}
	
	private long[][] handle(double[][] t)
	{
		int nb1 = t.length;
		int nb2 = nb1>0 ? t[0].length : 0;
		long[][] n = new long[nb1][nb2];
		
		for(int i=0;i<nb1;i++)
		for(int j=0;j<nb2;j++)
		n[i][j] = (long) t[i][j];
		
		return n;
	}
	
	private long[][] handle(float[][] t)
	{
		int nb1 = t.length;
		int nb2 = nb1>0 ? t[0].length : 0;
		long[][] n = new long[nb1][nb2];
		
		for(int i=0;i<nb1;i++)
		for(int j=0;j<nb2;j++)
		n[i][j] = (long) t[i][j];
		
		return n;
	}
	
	private long[][] handle(Object[][] t) throws Exception
	{
		int nb1 = t.length;
		int nb2 = nb1>0 ? t[0].length : 0;
		long[][] n = new long[nb1][nb2];
		
		for(int i=0;i<nb1;i++)
		for(int j=0;j<nb2;j++)
		n[i][j] = toLong(t[i][j]);
		
		return n;
	}
	
	
	
	private long[][] handle(int[] t)
	{
		long[][] n = new long[1][t.length];
		for(int i=0;i<t.length;i++)
		n[0][i] = (long) t[i];
		return n;
	}
	
	private long[][] handle(long[] t)
	{
		long[][] n = new long[1][t.length];
		for(int i=0;i<t.length;i++)
		n[0][i] = t[i];
		return n;
	}
	
	private long[][] handle(double[] t)
	{
		long[][] n = new long[1][t.length];
		for(int i=0;i<t.length;i++)
		n[0][i] = (long) t[i];
		return n;
	}
	
	private long[][] handle(float[] t)
	{
		long[][] n = new long[1][t.length];
		for(int i=0;i<t.length;i++)
		n[0][i] = (long) t[i];
		return n;
	}
	
	private long[][] handle(Object[] t) throws Exception
	{
		long[][] n = new long[1][t.length];
		for(int i=0;i<t.length;i++)
		n[0][i] = toLong(t[i]);
		return n;
	}
	
	private long[][] handle(Number t)
	{
		return new long[][]{{t.longValue()}};
	}
	
	private long toLong(Object obj) throws Exception
	{
		if(obj instanceof Integer) return ((Integer) obj).longValue();
		if(obj instanceof Long) return ((Long) obj).longValue();
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
