package a.entity.gus06.data.perform.rows.tolists;

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
	
	
	
	private List toList(Object[][] b)
	{
		List list = new ArrayList();
		for(Object[] row : b) list.add(row);
		return list;
	}
	
	private List toList(int[][] b)
	{
		List list = new ArrayList();
		for(int[] row : b) list.add(row);
		return list;
	}
	
	private List toList(long[][] b)
	{
		List list = new ArrayList();
		for(long[] row : b) list.add(row);
		return list;
	}
	
	private List toList(double[][] b)
	{
		List list = new ArrayList();
		for(double[] row : b) list.add(row);
		return list;
	}
	
	private List toList(float[][] b)
	{
		List list = new ArrayList();
		for(float[] row : b) list.add(row);
		return list;
	}
	
	private List toList(boolean[][] b)
	{
		List list = new ArrayList();
		for(boolean[] row : b) list.add(row);
		return list;
	}
	
	private List toList(char[][] b)
	{
		List list = new ArrayList();
		for(char[] row : b) list.add(row);
		return list;
	}
	
	private List toList(byte[][] b)
	{
		List list = new ArrayList();
		for(byte[] row : b) list.add(row);
		return list;
	}
	
	private List toList(short[][] b)
	{
		List list = new ArrayList();
		for(short[] row : b) list.add(row);
		return list;
	}
}
