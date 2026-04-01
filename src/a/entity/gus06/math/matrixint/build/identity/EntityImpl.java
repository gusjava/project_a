package a.entity.gus06.math.matrixint.build.identity;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180110";}
	
	
	public Object t(Object obj) throws Exception
	{
		int s = Integer.parseInt(""+obj);
		
		int[][] result = new int[s][s];
		
		for(int i=0;i<s;i++)
		for(int j=0;j<s;j++)
		result[i][j] = i==j?1:0;
		
		return result;
	}
}
