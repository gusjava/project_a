package a.entity.gus06.data.perform.rows.tolist;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180117";}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof Object[][])		return toList((Object[][]) obj);
		if(obj instanceof int[][])		return toList((int[][]) obj);
		if(obj instanceof long[][])		return toList((long[][]) obj);
		if(obj instanceof double[][])		return toList((double[][]) obj);
		if(obj instanceof float[][])		return toList((float[][]) obj);
		if(obj instanceof boolean[][])		return toList((boolean[][]) obj);
		if(obj instanceof char[][])		return toList((char[][]) obj);
		if(obj instanceof byte[][])		return toList((byte[][]) obj);
		if(obj instanceof short[][])		return toList((short[][]) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private List toList(Object[][] oo)
	{
		List l = new ArrayList();
		for(Object[] o:oo)
		for(Object b:o)
		l.add(b);
		return l;
	}
	
	private List toList(int[][] oo)
	{
		List l = new ArrayList();
		for(int[] o:oo)
		for(int b:o)
		l.add(Integer.valueOf(b));
		return l;
	}
	
	private List toList(long[][] oo)
	{
		List l = new ArrayList();
		for(long[] o:oo)
		for(long b:o)
		l.add(Long.valueOf(b));
		return l;
	}
	
	private List toList(double[][] oo)
	{
		List l = new ArrayList();
		for(double[] o:oo)
		for(double b:o)
		l.add(Double.valueOf(b));
		return l;
	}
	
	private List toList(float[][] oo)
	{
		List l = new ArrayList();
		for(float[] o:oo)
		for(float b:o)
		l.add(Float.valueOf(b));
		return l;
	}
	
	private List toList(boolean[][] oo)
	{
		List l = new ArrayList();
		for(boolean[] o:oo)
		for(boolean b:o)
		l.add(Boolean.valueOf(b));
		return l;
	}
	
	private List toList(char[][] oo)
	{
		List l = new ArrayList();
		for(char[] o:oo)
		for(char b:o)
		l.add(""+b);
		return l;
	}
	
	private List toList(byte[][] oo)
	{
		List l = new ArrayList();
		for(byte[] o:oo)
		for(byte b:o)
		l.add(Byte.valueOf(b));
		return l;
	}
	
	private List toList(short[][] oo)
	{
		List l = new ArrayList();
		for(short[] o:oo)
		for(short b:o)
		l.add(Short.valueOf(b));
		return l;
	}
}