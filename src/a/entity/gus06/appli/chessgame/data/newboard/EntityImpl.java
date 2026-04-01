package a.entity.gus06.appli.chessgame.data.newboard;

import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20150409";}

	/********************************/
	/* 1:pawn, 2:tower, 3:knight    */
	/* 4:bishop, 5:queen, 6:king    */
	/* -:black +:white              */
	/********************************/
	
	
	public Object g() throws Exception
	{
		return new int[][]{
			{-2,-3,-4,-5,-6,-4,-3,-2},
			{-1,-1,-1,-1,-1,-1,-1,-1},
			{0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0},
			{1,1,1,1,1,1,1,1},
			{2,3,4,5,6,4,3,2}
		};
	}
}
