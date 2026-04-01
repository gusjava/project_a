package a.entity.gus06.math.matrixlong.build.empty;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180110";}
	
	
	private Service buildDim;
	
	public EntityImpl() throws Exception
	{
		buildDim = Outside.service(this,"gus06.math.matrixdim.build");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		int[] s = (int[]) buildDim.t(obj);
		
		int x = s[0];
		int y = s[1];
		
		long[][] result = new long[x][y];
		
		for(int i=0;i<x;i++)
		for(int j=0;j<y;j++)
		result[i][j] = 0;
		
		return result;
	}
}
