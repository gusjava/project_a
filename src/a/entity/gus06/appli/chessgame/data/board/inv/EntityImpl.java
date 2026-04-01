package a.entity.gus06.appli.chessgame.data.board.inv;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191115";}

	public static final int NB = 8;
	
	
	public Object t(Object obj) throws Exception
	{
		int[][] d = (int[][]) obj;
		int[][] d1 = new int[NB][NB];
		for(int i=0;i<NB;i++) for(int j=0;j<NB;j++) d1[NB-1-i][j] = -1*d[i][j];
		return d1;
	}
}
