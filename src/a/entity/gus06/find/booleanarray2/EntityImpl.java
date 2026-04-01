package a.entity.gus06.find.booleanarray2;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180113";}


	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		if(obj instanceof boolean[][]) return obj;
		if(obj instanceof int[][]) return handle((int[][]) obj);
		if(obj instanceof long[][]) return handle((long[][]) obj);
		if(obj instanceof double[][]) return handle((double[][]) obj);
		if(obj instanceof float[][]) return handle((float[][]) obj);
		if(obj instanceof Object[][]) return handle((Object[][]) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private boolean convertInt(int t) throws Exception
	{
		if(t==1) return true;
		if(t==0) return false;
		throw new Exception("Invalid data: "+t);
	}
	
	private boolean convertLong(long t) throws Exception
	{
		if(t==1) return true;
		if(t==0) return false;
		throw new Exception("Invalid data: "+t);
	}
	
	private boolean convertDouble(double t) throws Exception
	{
		if(t==1) return true;
		if(t==0) return false;
		throw new Exception("Invalid data: "+t);
	}
	
	private boolean convertFloat(float t) throws Exception
	{
		if(t==1) return true;
		if(t==0) return false;
		throw new Exception("Invalid data: "+t);
	}
	
	private boolean convertObj(Object t) throws Exception
	{
		String s = ""+t;
		
		if(s.equals("true")) return true;
		if(s.equals("1")) return true;
		
		if(s.equals("false")) return false;
		if(s.equals("0")) return false;
		
		throw new Exception("Invalid data: "+t);
	}
	
	
	
	
	private boolean[][] handle(int[][] t) throws Exception
	{
		int nb1 = t.length;
		int nb2 = nb1>0 ? t[0].length : 0;
		boolean[][] n = new boolean[nb1][nb2];
		
		for(int i=0;i<nb1;i++)
		for(int j=0;j<nb2;j++)
		n[i][j] = convertInt(t[i][j]);
		
		return n;
	}
	
	private boolean[][] handle(long[][] t) throws Exception
	{
		int nb1 = t.length;
		int nb2 = nb1>0 ? t[0].length : 0;
		boolean[][] n = new boolean[nb1][nb2];
		
		for(int i=0;i<nb1;i++)
		for(int j=0;j<nb2;j++)
		n[i][j] = convertLong(t[i][j]);
		
		return n;
	}
	
	private boolean[][] handle(double[][] t) throws Exception
	{
		int nb1 = t.length;
		int nb2 = nb1>0 ? t[0].length : 0;
		boolean[][] n = new boolean[nb1][nb2];
		
		for(int i=0;i<nb1;i++)
		for(int j=0;j<nb2;j++)
		n[i][j] = convertDouble(t[i][j]);
		
		return n;
	}
	
	private boolean[][] handle(float[][] t) throws Exception
	{
		int nb1 = t.length;
		int nb2 = nb1>0 ? t[0].length : 0;
		boolean[][] n = new boolean[nb1][nb2];
		
		for(int i=0;i<nb1;i++)
		for(int j=0;j<nb2;j++)
		n[i][j] = convertFloat(t[i][j]);
		
		return n;
	}
	
	private boolean[][] handle(Object[][] t) throws Exception
	{
		int nb1 = t.length;
		int nb2 = nb1>0 ? t[0].length : 0;
		boolean[][] n = new boolean[nb1][nb2];
		
		for(int i=0;i<nb1;i++)
		for(int j=0;j<nb2;j++)
		n[i][j] = convertObj(t[i][j]);
		
		return n;
	}
	
	private boolean[] handle(boolean[][] d) throws Exception
	{
		if(d.length==1) return d[0];
		if(d.length>1 && d[0].length==1)
		{
			int l = d.length;
			boolean[] r = new boolean[l];
			for(int i=0;i<l;i++) r[i] = d[i][0];
			return r;
		}
		throw new Exception("Invalid array length: "+d.length);
	}
}
