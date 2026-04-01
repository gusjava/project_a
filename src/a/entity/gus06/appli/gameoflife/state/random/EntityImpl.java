package a.entity.gus06.appli.gameoflife.state.random;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250715";}
		
	public Object t(Object obj) throws Exception
	{
		int size = Integer.parseInt(""+obj);
		
		boolean[][] b = new boolean[size][size];
		for(int i=0;i<b.length;i++)
		for(int j=0;j<b.length;j++)
		b[i][j] = Math.random()<0.5;
		return b;
	}
}