package a.entity.gus06.data.perform.colnb;

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
		int n = b.length;
		return Integer.valueOf(n>0 ? b[0].length : 0);
	}
	
	private Integer nb(double[][] b)
	{
		int n = b.length;
		return Integer.valueOf(n>0 ? b[0].length : 0);
	}
	
	private Integer nb(int[][] b)
	{
		int n = b.length;
		return Integer.valueOf(n>0 ? b[0].length : 0);
	}
	
	private Integer nb(long[][] b)
	{
		int n = b.length;
		return Integer.valueOf(n>0 ? b[0].length : 0);
	}
	
	private Integer nb(float[][] b)
	{
		int n = b.length;
		return Integer.valueOf(n>0 ? b[0].length : 0);
	}
	
	private Integer nb(boolean[][] b)
	{
		int n = b.length;
		return Integer.valueOf(n>0 ? b[0].length : 0);
	}
	
	private Integer nb(char[][] b)
	{
		int n = b.length;
		return Integer.valueOf(n>0 ? b[0].length : 0);
	}
	
	private Integer nb(byte[][] b)
	{
		int n = b.length;
		return Integer.valueOf(n>0 ? b[0].length : 0);
	}
	
	private Integer nb(short[][] b)
	{
		int n = b.length;
		return Integer.valueOf(n>0 ? b[0].length : 0);
	}
	
}
