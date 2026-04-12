package a.entity.gus06.appli.vindinium.engine.handleturn;

import java.util.List;
import java.util.Map;

public class Turn0 {

	
	
	protected Map game;
	protected Map board;
	protected List heroes;
	
	
	
	
	
	
	protected void initGame(Object obj)
	{
		game = (Map) obj;
		board = (Map) game.get(DATA.G_BOARD);
		heroes = (List) game.get(DATA.G_HEROES);
	}
	
	
	
	
	
	protected Map heroMap(int index)
	{return (Map) heroes.get(index);}
	
	
	protected int heroId(int index)
	{return i_(heroMap(index).get(DATA.H_ID));}
	
	
	protected int heroMineCode(int index)
	{return heroId(index)+10;}
	
	
	protected int[] heroPos(int index)
	{return ii_(heroMap(index).get(DATA.H_POS));}
	
	
	protected int[] heroSpawnPos(int index)
	{return ii_(heroMap(index).get(DATA.H_SPAWNPOS));}
	
	
	protected int heroLife(int index)
	{return i_(heroMap(index).get(DATA.H_LIFE));}
	
	
	protected int heroGold(int index)
	{return i_(heroMap(index).get(DATA.H_GOLD));}
	
	
	
	protected void setHeroPos(int index, int[] pos)
	{
		Map m = (Map) heroMap(index).get(DATA.H_POS);
		updatePos(m,pos);
	}
	
	

	
	
	
	
	protected void setHeroLife(int index, int life)
	{heroMap(index).put(DATA.H_LIFE,""+life);}
	
	
	protected void setHeroGold(int index, int gold)
	{heroMap(index).put(DATA.H_GOLD,""+gold);}
	
	
	protected void setHeroMineCount(int index, int count)
	{heroMap(index).put(DATA.H_MINECOUNT,""+count);}
	
	
	
	
	private void updatePos(Map m, int[] pos)
	{
		m.put(DATA.X,""+pos[0]);
		m.put(DATA.Y,""+pos[1]);
	}
	
	
	
	
	private int i_(Object s)
	{
		if(s==null) return -1;
		return Integer.parseInt((String) s);
	}
	
	
	private int[] ii_(Object o)
	{
		Map m = (Map) o;
		return new int[]{
				i_(m.get(DATA.X)),
				i_(m.get(DATA.Y))};
	}
	
	protected boolean equals(int[] p1, int[] p2)
	{return p1!=null && p2!=null && p1[0]==p2[0] && p1[1]==p2[1];}
}
