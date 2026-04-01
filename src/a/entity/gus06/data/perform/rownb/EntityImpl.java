package a.entity.gus06.data.perform.rownb;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180110";}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof Object[][])		return nb((Object[][]) obj);
		if(obj instanceof double[][])		return nb((double[][]) obj);
		if(obj instanceof int[][])		return nb((int[][]) obj);
		if(obj instanceof long[][])		return nb((long[][]) obj);
		if(obj instanceof float[][])		return nb((float[][]) obj);
		if(obj instanceof boolean[][])		return nb((boolean[][]) obj);
		if(obj instanceof char[][])		return nb((char[][]) obj);
		if(obj instanceof byte[][])		return nb((byte[][]) obj);
		if(obj instanceof short[][])		return nb((short[][]) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private Integer nb(Object[][] b)
	{
		return Integer.valueOf(b.length);
	}
	
	private Integer nb(double[][] b)
	{
		return Integer.valueOf(b.length);
	}
	
	private Integer nb(int[][] b)
	{
		return Integer.valueOf(b.length);
	}
	
	private Integer nb(long[][] b)
	{
		return Integer.valueOf(b.length);
	}
	
	private Integer nb(float[][] b)
	{
		return Integer.valueOf(b.length);
	}
	
	private Integer nb(boolean[][] b)
	{
		return Integer.valueOf(b.length);
	}
	
	private Integer nb(char[][] b)
	{
		return Integer.valueOf(b.length);
	}
	
	private Integer nb(byte[][] b)
	{
		return Integer.valueOf(b.length);
	}
	
	private Integer nb(short[][] b)
	{
		return Integer.valueOf(b.length);
	}
	
}
