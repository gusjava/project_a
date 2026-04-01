package a.entity.gus06.appli.chessgame.data.issafe.forhim.hisking;

import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20150412";}

	public static final int NB = 8;

	private Service isSafeCell;

	public EntityImpl() throws Exception
	{
		isSafeCell = Outside.service(this,"gus06.appli.chessgame.data.issafe.forhim.cell");
	}
	
	
	
	public boolean f(Object obj) throws Exception
	{
		int[][] data = (int[][]) obj;
		int[] hisKing = findHisKing(data);
		return isSafeCell.f(new Object[]{data,hisKing});
	}
	
	
	private int[] findHisKing(int[][] data) throws Exception
	{
		for(int i=0;i<NB;i++)for(int j=0;j<NB;j++)
		if(data[i][j]==-6) return new int[]{i,j};
		throw new Exception("King not found");
	}
}
