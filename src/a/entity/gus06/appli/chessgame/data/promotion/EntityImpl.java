package a.entity.gus06.appli.chessgame.data.promotion;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150410";}

	/********************************/
	/* 1:pawn, 2:tower, 3:knight    */
	/* 4:bishop, 5:queen, 6:king    */
	/* -:black +:white              */
	/********************************/
	
	public static final int NB = 8;
	
	
	public void p(Object obj) throws Exception
	{
		int[][] data = (int[][]) obj;
		
		for(int i=0;i<NB;i++)
		{
			if(data[NB-1][i]==-1) data[NB-1][i] = -5; //black pawn promoted
			if(data[0][i]==1) data[0][i] = 5; //white pawn promoted
		}
	}
}
