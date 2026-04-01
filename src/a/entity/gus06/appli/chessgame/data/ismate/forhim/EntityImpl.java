package a.entity.gus06.appli.chessgame.data.ismate.forhim;

import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20150412";}

	public static final int NB = 8;
	

	private Service myKingIsSafe;
	private Service checkMove;
	private Service inv;
	private Service clone;
	
	public EntityImpl() throws Exception
	{
		myKingIsSafe = Outside.service(this,"gus06.appli.chessgame.data.issafe.forme.myking");
		checkMove = Outside.service(this,"gus06.appli.chessgame.engine.checkmove");
		inv = Outside.service(this,"gus06.appli.chessgame.data.board.inv");
		clone = Outside.service(this,"gus06.appli.chessgame.data.board.clone");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		int[][] data = (int[][]) obj;
		int[][] data_ = (int[][]) inv.t(data);
		
		for(int i=0;i<NB;i++) for(int j=0;j<NB;j++)
		{
			int value0 = data_[i][j];
			if(value0>0)
			{
				for(int k=0;k<NB;k++) for(int l=0;l<NB;l++)
				{
					int value1 = data_[k][l];
					if(value1<=0)
					{
						int[] start = new int[]{i,j};
						int[] end = new int[]{k,l};
						if(checkMove.f(new Object[]{data_,start,end}))
						{
							int[][] data1 = (int[][]) clone.t(data_);
							int v = data1[i][j];
							data1[k][l] = v;
							data1[i][j] = 0;
							
							if(myKingIsSafe.f(data1)) return false;
						}
					}
				}
			}
		}
		return true;
	}
}
