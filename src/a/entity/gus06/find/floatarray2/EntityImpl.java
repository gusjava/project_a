package a.entity.gus06.find.floatarray2;

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
		
		if(obj instanceof float[][]) return obj;
		if(obj instanceof int[][]) return handle((int[][]) obj);
		if(obj instanceof long[][]) return handle((long[][]) obj);
		if(obj instanceof double[][]) return handle((double[][]) obj);
		if(obj instanceof Object[][]) return handle((Object[][]) obj);
		
		if(obj instanceof int[]) return handle((int[]) obj);
		if(obj instanceof long[]) return handle((long[]) obj);
		if(obj instanceof double[]) return handle((double[]) obj);
		if(obj instanceof float[]) return handle((float[]) obj);
		if(obj instanceof Object[]) return handle((Object[]) obj);
		
		if(obj instanceof Number) return handle((Number) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private float[][] handle(int[][] t)
	{
		int nb1 = t.length;
		int nb2 = nb1>0 ? t[0].length : 0;
		float[][] n = new float[nb1][nb2];
		
		for(int i=0;i<nb1;i++)
		for(int j=0;j<nb2;j++)
		n[i][j] = (float) t[i][j];
		
		return n;
	}
	
	private float[][] handle(long[][] t)
	{
		int nb1 = t.length;
		int nb2 = nb1>0 ? t[0].length : 0;
		float[][] n = new float[nb1][nb2];
		
		for(int i=0;i<nb1;i++)
		for(int j=0;j<nb2;j++)
		n[i][j] = (float) t[i][j];
		
		return n;
	}
	
	private float[][] handle(double[][] t)
	{
		int nb1 = t.length;
		int nb2 = nb1>0 ? t[0].length : 0;
		float[][] n = new float[nb1][nb2];
		
		for(int i=0;i<nb1;i++)
		for(int j=0;j<nb2;j++)
		n[i][j] = (float) t[i][j];
		
		return n;
	}
	
	private float[][] handle(Object[][] t)
	{
		int nb1 = t.length;
		int nb2 = nb1>0 ? t[0].length : 0;
		float[][] n = new float[nb1][nb2];
		
		for(int i=0;i<nb1;i++)
		for(int j=0;j<nb2;j++)
		n[i][j] = toFloat(t[i][j]);
		
		return n;
	}
	
	
	
	private float[][] handle(int[] t)
	{
		float[][] n = new float[1][t.length];
		for(int i=0;i<t.length;i++)
		n[0][i] = (float) t[i];
		return n;
	}
	
	private float[][] handle(long[] t)
	{
		float[][] n = new float[1][t.length];
		for(int i=0;i<t.length;i++)
		n[0][i] = (float) t[i];
		return n;
	}
	
	private float[][] handle(double[] t)
	{
		float[][] n = new float[1][t.length];
		for(int i=0;i<t.length;i++)
		n[0][i] = (float) t[i];
		return n;
	}
	
	private float[][] handle(float[] t)
	{
		float[][] n = new float[1][t.length];
		for(int i=0;i<t.length;i++)
		n[0][i] = t[i];
		return n;
	}
	
	private float[][] handle(Object[] t)
	{
		float[][] n = new float[1][t.length];
		for(int i=0;i<t.length;i++)
		n[0][i] = toFloat(t[i]);
		return n;
	}
	
	private float[][] handle(Number t)
	{
		return new float[][]{{t.floatValue()}};
	}
	
	private float toFloat(Object obj)
	{return ((Number) obj).floatValue();}
}
