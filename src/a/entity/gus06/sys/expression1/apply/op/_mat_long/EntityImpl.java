package a.entity.gus06.sys.expression1.apply.op._mat_long;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180115";}
	
	
	private Service toDim;
	
	public EntityImpl() throws Exception
	{
		toDim = Outside.service(this,"gus06.math.matrixdim.build");
	}

	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		int[] dim = toDim(obj);
		return new T1(dim[0],dim[1]);
	}
	
	
	private int[] toDim(Object obj) throws Exception
	{return (int[]) toDim.t(obj);}
	
	
	
	private class T1 implements T
	{
		private int x;
		private int y;
		
		public T1(int x, int y)
		{
			this.x = x;
			this.y = y;
		}
		
		public Object t(Object obj) throws Exception
		{
			long value = ((Number) obj).longValue();
			return buildMatrix(x,y,value);
		}
	}
	
	
	private long[][] buildMatrix(int x, int y, long value) throws Exception
	{
		long[][] matrix = new long[x][y];
		
		for(int i=0;i<x;i++)
		for(int j=0;j<y;j++)
		matrix[i][j] = value;
		
		return matrix;
	}
}
