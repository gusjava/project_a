package a.entity.gus06.sys.expression1.apply.op._mat_int_zero;

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
		return buildMatrix(dim[0],dim[1]);
	}
	
	
	private int[] toDim(Object obj) throws Exception
	{return (int[]) toDim.t(obj);}
	
	
	private int[][] buildMatrix(int x, int y) throws Exception
	{
		int[][] matrix = new int[x][y];
		
		for(int i=0;i<x;i++)
		for(int j=0;j<y;j++)
		matrix[i][j] = 0;
		
		return matrix;
	}
}
