package a.entity.gus06.sys.expression1.apply.op._mat_int_id;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180115";}
	
	
	private Service toDim;
	
	public EntityImpl() throws Exception
	{
		toDim = Outside.service(this,"gus06.math.matrixdim.build.square");
	}

	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		int[] dim = toDim(obj);
		return buildMatrix(dim[0]);
	}
	
	
	private int[] toDim(Object obj) throws Exception
	{return (int[]) toDim.t(obj);}
	
	
	private int[][] buildMatrix(int n) throws Exception
	{
		int[][] matrix = new int[n][n];
		
		for(int i=0;i<n;i++)
		for(int j=0;j<n;j++)
		matrix[i][j] = i==j ? 1 : 0;
		
		return matrix;
	}
}
