package a.entity.gus06.appli.chessgame.data.issafe.forme.cell;

import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20150411";}

	public static final int NB = 8;

	private Service isCellSafe;
	private Service inv;
	
	public EntityImpl() throws Exception
	{
		isCellSafe = Outside.service(this,"gus06.appli.chessgame.data.issafe.forhim.cell");
		inv = Outside.service(this,"gus06.appli.chessgame.data.board.inv");
	}
	
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		int[][] data = (int[][]) o[0];
		int[] cell = (int[]) o[1];
		
		return isCellSafe.f(new Object[]{inv(data),inv(cell)});
	}
	
	private int[][] inv(int[][] d) throws Exception
	{return (int[][]) inv.t(d);}
	
	private int[] inv(int[] p)
	{return new int[]{NB-1-p[0],p[1]};}
}
