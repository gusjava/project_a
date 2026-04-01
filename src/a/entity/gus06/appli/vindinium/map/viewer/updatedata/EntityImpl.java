package a.entity.gus06.appli.vindinium.map.viewer.updatedata;

import java.util.List;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20170917";}

	private Map game;
	private Map hero;
	private List heros;

	public EntityImpl() throws Exception
	{
	}


	public void p(Object obj) throws Exception
	{
		Object[] t = (Object[]) obj;
		if(t.length!=2) throw new Exception("Wrong data number: "+t.length);
		
		Map data = (Map) t[0];
		int[][] tiles = (int[][]) t[1];
		
		game = (Map) data.get(DATA.K_GAME);
		hero = (Map) data.get(DATA.K_HERO);
		heros = (List) game.get(DATA.G_HEROES);
		
		int size = tiles.length;
		
		for(int i=0;i<size;i++)for(int j=0;j<size;j++)
		{
			int v = tiles[i][j];
			switch(v) {
			case TILE.HERO1:handleHero(v,i,j);
			case TILE.HERO2:handleHero(v,i,j);
			case TILE.HERO3:handleHero(v,i,j);
			case TILE.HERO4:handleHero(v,i,j);
			}
		}
	}
	
	
	private void handleHero(int id, int i, int j)
	{
		Map h = (Map) heros.get(id-1);
		
		updatePos(h.get(DATA.H_POS),i,j);
		updatePos(h.get(DATA.H_SPAWNPOS),i,j);

		if(id==1)
		{
			updatePos(hero.get(DATA.H_POS),i,j);
			updatePos(hero.get(DATA.H_SPAWNPOS),i,j);
		}
	}
	
	private void updatePos(Object pos, int i, int j)
	{
		Map m = (Map) pos;
		m.put(DATA.X,""+i);
		m.put(DATA.Y,""+j);
	}
}
