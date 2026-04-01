package a.entity.gus06.data.perform.infertype.fromarray;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180213";}

	
	public Object t(Object obj) throws Exception
	{return inferFromArray((Object[]) obj);}
	
	
	
	private Object inferFromArray(Object[] array)
	{
		int[] t = buildIntArray(array);
		if(t!=null) return t;
		
		long[] l = buildLongArray(array);
		if(l!=null) return l;
		
		double[] d = buildDoubleArray(array);
		if(d!=null) return d;
		
		boolean[] b = buildBooleanArray(array);
		if(b!=null) return b;
		
		String[] s = buildStringArray(array);
		if(s!=null) return s;
		
		return array;
	}
	
	
	private int[] buildIntArray(Object[] array)
	{
		int len = array.length;
		int[] t = new int[len];
		for(int i=0;i<len;i++)
		{
			try{t[i] = Integer.parseInt(""+array[i]);}
			catch(NumberFormatException e)
			{return null;}
		}
		return t;
	}
	
	private long[] buildLongArray(Object[] array)
	{
		int len = array.length;
		long[] t = new long[len];
		for(int i=0;i<len;i++)
		{
			try{t[i] = Long.parseLong(""+array[i]);}
			catch(NumberFormatException e)
			{return null;}
		}
		return t;
	}
	
	private double[] buildDoubleArray(Object[] array)
	{
		int len = array.length;
		double[] t = new double[len];
		for(int i=0;i<len;i++)
		{
			try{t[i] = Double.parseDouble(""+array[i]);}
			catch(NumberFormatException e)
			{return null;}
		}
		return t;
	}
	
	private boolean[] buildBooleanArray(Object[] array)
	{
		int len = array.length;
		boolean[] t = new boolean[len];
		for(int i=0;i<len;i++)
		{
			Boolean b = inferBoolean(array[i]);
			if(b==null) return null;
			t[i] = b.booleanValue();
		}
		return t;
	}
	
	private String[] buildStringArray(Object[] array)
	{
		int len = array.length;
		String[] t = new String[len];
		for(int i=0;i<len;i++)
		{
			String s = inferString(array[i]);
			if(s==null) return null;
			t[i] = s;
		}
		return t;
	}
	
	
	private Boolean inferBoolean(Object obj)
	{
		if(obj==null) return null;
		if(obj instanceof Boolean) return (Boolean) obj;
		if(obj instanceof String)
		{
			String s = ((String) obj).toLowerCase();
			if(s.equals("true")) return Boolean.TRUE;
			if(s.equals("false")) return Boolean.FALSE;
			return null;
		}
		if(obj instanceof Integer)
		{
			int n = ((Integer) obj).intValue();
			if(n==1) return Boolean.TRUE;
			if(n==0) return Boolean.FALSE;
			return null;
		}
		if(obj instanceof Long)
		{
			int n = ((Long) obj).intValue();
			if(n==1) return Boolean.TRUE;
			if(n==0) return Boolean.FALSE;
			return null;
		}
		return null;
	}
	
	
	private String inferString(Object obj)
	{
		if(obj==null) return null;
		if(obj instanceof String) return (String) obj;
		if(obj instanceof Number) return ""+obj;
		if(obj instanceof Boolean) return ""+obj;
		return null;
	}
}
