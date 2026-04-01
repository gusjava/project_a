package a.entity.gus06.appli.chessgame.data.issafe.forme.myking;

import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20150411";}

	public static final int NB = 8;

	private Service isSafeCell;

	public EntityImpl() throws Exception
	{isSafeCell = Outside.service(this,"gus06.appli.chessgame.data.issafe.forme.cell");}
	
	
	
	public boolean f(Object obj) throws Exception
	{
		int[][] data = (int[][]) obj;
		int[] myKing = findMyKing(data);
		return isSafeCell.f(new Object[]{data,myKing});
	}
	
	
	private int[] findMyKing(int[][] data) throws Exception
	{
		for(int i=0;i<NB;i++)for(int j=0;j<NB;j++)
		if(data[i][j]==6) return new int[]{i,j};
		throw new Exception("King not found");
	}
}
