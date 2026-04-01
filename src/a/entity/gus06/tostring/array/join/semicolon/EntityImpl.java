package a.entity.gus06.tostring.array.join.semicolon;

import java.util.List;
import a.framework.*;
import java.awt.Dimension;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170518";}
	
	public static final String DELIM = ";";



	public Object t(Object obj) throws Exception
	{
		if(obj instanceof int[]) return intArrayToString((int[]) obj);
		if(obj instanceof long[]) return longArrayToString((long[]) obj);
		if(obj instanceof double[]) return doubleArrayToString((double[]) obj);
		if(obj instanceof float[]) return floatArrayToString((float[]) obj);
		if(obj instanceof boolean[]) return booleanArrayToString((boolean[]) obj);
		if(obj instanceof Dimension) return dimensionToString((Dimension) obj);
		
		if(obj instanceof Object[]) return arrayToString((Object[]) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private String intArrayToString(int[] array) throws Exception
	{
		StringBuffer b = new StringBuffer();
		int nb = array.length;
		for(int i=0;i<nb;i++)
		{
			b.append(array[i]);
			if(i<nb-1) b.append(DELIM);
		}
		return b.toString();
	}
	
	private String dimensionToString(Dimension dim) throws Exception
	{
		return dim.width+DELIM+dim.height;
	}
	
	private String longArrayToString(long[] array) throws Exception
	{
		StringBuffer b = new StringBuffer();
		int nb = array.length;
		for(int i=0;i<nb;i++)
		{
			b.append(array[i]);
			if(i<nb-1) b.append(DELIM);
		}
		return b.toString();
	}
	
	private String doubleArrayToString(double[] array) throws Exception
	{
		StringBuffer b = new StringBuffer();
		int nb = array.length;
		for(int i=0;i<nb;i++)
		{
			b.append(array[i]);
			if(i<nb-1) b.append(DELIM);
		}
		return b.toString();
	}
	
	private String floatArrayToString(float[] array) throws Exception
	{
		StringBuffer b = new StringBuffer();
		int nb = array.length;
		for(int i=0;i<nb;i++)
		{
			b.append(array[i]);
			if(i<nb-1) b.append(DELIM);
		}
		return b.toString();
	}
	
	private String booleanArrayToString(boolean[] array) throws Exception
	{
		StringBuffer b = new StringBuffer();
		int nb = array.length;
		for(int i=0;i<nb;i++)
		{
			b.append(""+array[i]);
			if(i<nb-1) b.append(DELIM);
		}
		return b.toString();
	}
	
	private String arrayToString(Object[] array) throws Exception
	{
		StringBuffer b = new StringBuffer();
		for(int i=0;i<array.length;i++)
		{
			String el = "" + array[i];
			if(el.contains(DELIM)) throw new Exception("Invalid element syntax: "+el);
			b.append(el+DELIM);
		}
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		return b.toString();
	}
}
