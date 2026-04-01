package a.entity.gus06.find.intarray2;

import a.framework.*;
import java.util.*;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180107";}


	
	public EntityImpl() throws Exception
	{
	}

	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		if(obj instanceof int[][]) return obj;
		if(obj instanceof long[][]) return handle((long[][]) obj);
		if(obj instanceof double[][]) return handle((double[][]) obj);
		if(obj instanceof float[][]) return handle((float[][]) obj);
		if(obj instanceof boolean[][]) return handle((boolean[][]) obj);
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
	
	
	
	private int[][] handle(long[][] t)
	{
		int nb1 = t.length;
		int nb2 = nb1>0 ? t[0].length : 0;
		int[][] n = new int[nb1][nb2];
		
		for(int i=0;i<nb1;i++)
		for(int j=0;j<nb2;j++)
		n[i][j] = (int) t[i][j];
		
		return n;
	}
	
	private int[][] handle(double[][] t)
	{
		int nb1 = t.length;
		int nb2 = nb1>0 ? t[0].length : 0;
		int[][] n = new int[nb1][nb2];
		
		for(int i=0;i<nb1;i++)
		for(int j=0;j<nb2;j++)
		n[i][j] = (int) t[i][j];
		
		return n;
	}
	
	private int[][] handle(float[][] t)
	{
		int nb1 = t.length;
		int nb2 = nb1>0 ? t[0].length : 0;
		int[][] n = new int[nb1][nb2];
		
		for(int i=0;i<nb1;i++)
		for(int j=0;j<nb2;j++)
		n[i][j] = (int) t[i][j];
		
		return n;
	}
	
	private int[][] handle(boolean[][] t)
	{
		int nb1 = t.length;
		int nb2 = nb1>0 ? t[0].length : 0;
		int[][] n = new int[nb1][nb2];
		
		for(int i=0;i<nb1;i++)
		for(int j=0;j<nb2;j++)
		n[i][j] = t[i][j] ? 1 : 0;
		
		return n;
	}
	
	private int[][] handle(Object[][] t) throws Exception
	{
		int nb1 = t.length;
		int nb2 = nb1>0 ? t[0].length : 0;
		int[][] n = new int[nb1][nb2];
		
		for(int i=0;i<nb1;i++)
		for(int j=0;j<nb2;j++)
		n[i][j] = toInt(t[i][j]);
		
		return n;
	}
	
	
	
	private int[][] handle(int[] t)
	{
		int[][] n = new int[1][t.length];
		for(int i=0;i<t.length;i++)
		n[0][i] = t[i];
		return n;
	}
	
	private int[][] handle(long[] t)
	{
		int[][] n = new int[1][t.length];
		for(int i=0;i<t.length;i++)
		n[0][i] = (int) t[i];
		return n;
	}
	
	private int[][] handle(double[] t)
	{
		int[][] n = new int[1][t.length];
		for(int i=0;i<t.length;i++)
		n[0][i] = (int) t[i];
		return n;
	}
	
	private int[][] handle(float[] t)
	{
		int[][] n = new int[1][t.length];
		for(int i=0;i<t.length;i++)
		n[0][i] = (int) t[i];
		return n;
	}
	
	private int[][] handle(Object[] t) throws Exception
	{
		int[][] n = new int[1][t.length];
		for(int i=0;i<t.length;i++)
		n[0][i] = toInt(t[i]);
		return n;
	}
	
	private int[][] handle(Number t)
	{
		return new int[][]{{t.intValue()}};
	}
	
	private int toInt(Object obj) throws Exception
	{
		if(obj instanceof Integer) return ((Integer) obj).intValue();
		if(obj instanceof Long) return ((Long) obj).intValue();
		if(obj instanceof Boolean) return ((Boolean) obj).booleanValue()?1:0;
		if(obj instanceof String) return Integer.parseInt((String) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
