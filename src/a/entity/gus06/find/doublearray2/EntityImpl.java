package a.entity.gus06.find.doublearray2;

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
		
		if(obj instanceof double[][]) return obj;
		if(obj instanceof int[][]) return handle((int[][]) obj);
		if(obj instanceof long[][]) return handle((long[][]) obj);
		if(obj instanceof float[][]) return handle((float[][]) obj);
		if(obj instanceof Object[][]) return handle((Object[][]) obj);
		
		if(obj instanceof int[]) return handle((int[]) obj);
		if(obj instanceof long[]) return handle((long[]) obj);
		if(obj instanceof double[]) return handle((double[]) obj);
		if(obj instanceof float[]) return handle((float[]) obj);
		if(obj instanceof Object[]) return handle((Object[]) obj);
		
		if(obj instanceof Integer) return handle((Integer) obj);
		if(obj instanceof Long) return handle((Long) obj);
		if(obj instanceof Double) return handle((Double) obj);
		if(obj instanceof Float) return handle((Float) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private double[][] handle(int[][] t)
	{
		int nb1 = t.length;
		int nb2 = nb1>0 ? t[0].length : 0;
		double[][] n = new double[nb1][nb2];
		
		for(int i=0;i<nb1;i++)
		for(int j=0;j<nb2;j++)
		n[i][j] = (double) t[i][j];
		
		return n;
	}
	
	private double[][] handle(long[][] t)
	{
		int nb1 = t.length;
		int nb2 = nb1>0 ? t[0].length : 0;
		double[][] n = new double[nb1][nb2];
		
		for(int i=0;i<nb1;i++)
		for(int j=0;j<nb2;j++)
		n[i][j] = (double) t[i][j];
		
		return n;
	}
	
	private double[][] handle(float[][] t)
	{
		int nb1 = t.length;
		int nb2 = nb1>0 ? t[0].length : 0;
		double[][] n = new double[nb1][nb2];
		
		for(int i=0;i<nb1;i++)
		for(int j=0;j<nb2;j++)
		n[i][j] = (double) t[i][j];
		
		return n;
	}
	
	private double[][] handle(Object[][] t)
	{
		int nb1 = t.length;
		int nb2 = nb1>0 ? t[0].length : 0;
		double[][] n = new double[nb1][nb2];
		
		for(int i=0;i<nb1;i++)
		for(int j=0;j<nb2;j++)
		n[i][j] = toDouble(t[i][j]);
		
		return n;
	}
	
	
	
	private double[][] handle(int[] t)
	{
		double[][] n = new double[1][t.length];
		for(int i=0;i<t.length;i++)
		n[0][i] = (double) t[i];
		return n;
	}
	
	private double[][] handle(long[] t)
	{
		double[][] n = new double[1][t.length];
		for(int i=0;i<t.length;i++)
		n[0][i] = (double) t[i];
		return n;
	}
	
	private double[][] handle(double[] t)
	{
		double[][] n = new double[1][t.length];
		for(int i=0;i<t.length;i++)
		n[0][i] = t[i];
		return n;
	}
	
	private double[][] handle(float[] t)
	{
		double[][] n = new double[1][t.length];
		for(int i=0;i<t.length;i++)
		n[0][i] = (double) t[i];
		return n;
	}
	
	private double[][] handle(Object[] t)
	{
		double[][] n = new double[1][t.length];
		for(int i=0;i<t.length;i++)
		n[0][i] = toDouble(t[i]);
		return n;
	}
	
	private double[][] handle(Number t)
	{
		return new double[][]{{t.doubleValue()}};
	}
	
	
	private double toDouble(Object obj)
	{return ((Number) obj).doubleValue();}
}
