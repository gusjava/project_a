package a.entity.gus06.appli.vindinium.data.retrievedata.format.heros;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20170923";}


	private Service ranking;
	private Service nextToBeer;
	private Map data;
	

	public EntityImpl() throws Exception
	{
		ranking = Outside.service(this,"gus06.math.tabint.ranking");
		nextToBeer = Outside.service(this,"gus06.appli.vindinium.bot.tool.tile.isnexttobeer");
	}
	
	private void put(String key, Object value)
	{data.put(key,value);}


	public void p(Object obj) throws Exception
	{
		if(obj==null) return;
		
		data = (Map) obj;
		
		Map game = (Map) data.get(DATA.K_GAME);
		List heroes = (List) game.get(DATA.G_HEROES);
		int[][] tiles = (int[][]) data.get(DATA_._BOARD);
		
		if(heroes.size()!=4) throw new Exception("Invalid hero number: "+heroes.size());
		
		int[] _h_elo = new int[4];
		int[] _h_gold = new int[4];
		int[] _h_life = new int[4];
		int[] _h_mine = new int[4];
		int[] _h_away = new int[4];
		
		int[] _h_pkilled = new int[4];
		int[] _h_pdrink = new int[4];
		int[] _h_pmine = new int[4];
		int[] _h_phit = new int[4];
		int[] _h_fgold = new int[4];
		
		int[][] _h_pos = new int[4][2];
		int[][] _h_pos0 = new int[4][2];
		int[][] _h_start = new int[4][2];
		
		String[] _h_name = new String[4];
		
		boolean[] _h_crashed = new boolean[4];
		boolean[] _h_resting = new boolean[4];
		boolean[] _h_immobile = new boolean[4];

		List[] _h_path = new List[4];
		List[] _h_rgold = new List[4];
		List[] _h_rlife = new List[4];
		
		for(int i=0;i<4;i++)
		{
			Map h_map = (Map) heroes.get(i);
			
			_h_elo[i] = i_(h_map.get(DATA.H_ELO));
			_h_gold[i] = i_(h_map.get(DATA.H_GOLD));
			_h_life[i] = i_(h_map.get(DATA.H_LIFE));
			_h_mine[i] = i_(h_map.get(DATA.H_MINECOUNT));
			
			_h_pkilled[i] = 0;
			_h_pdrink[i] = 0;
			_h_pmine[i] = 0;
			_h_phit[i] = 0;
			_h_fgold[i] = 0;
			
			_h_pos0[i] = null;
			_h_pos[i] = ii_(h_map.get(DATA.H_POS));
			_h_start[i] = ii_(h_map.get(DATA.H_SPAWNPOS));
			_h_away[i] = distance(_h_pos[i],_h_start[i]);
			
			_h_name[i] = (String) h_map.get(DATA.H_NAME);
			
			_h_crashed[i] = b_(h_map.get(DATA.H_CRASHED));
			_h_resting[i] = nextToBeer(tiles,_h_pos[i]);
			_h_immobile[i] = false;
			
			_h_path[i] = new ArrayList();
			_h_rgold[i] = new ArrayList();
			_h_rlife[i] = new ArrayList();
		}

		put(DATA_H_._H_ELO,_h_elo);
		put(DATA_H_._H_GOLD,_h_gold);
		put(DATA_H_._H_LIFE,_h_life);
		put(DATA_H_._H_MINE,_h_mine);
		put(DATA_H_._H_AWAY,_h_away);

		put(DATA_H_._H_PKILLED,_h_pkilled);
		put(DATA_H_._H_PDRINK,_h_pdrink);
		put(DATA_H_._H_PMINE,_h_pmine);
		put(DATA_H_._H_PHIT,_h_phit);
		put(DATA_H_._H_FGOLD,_h_fgold);
		
		put(DATA_H_._H_POS,_h_pos);
		put(DATA_H_._H_POS0,_h_pos0);
		put(DATA_H_._H_START,_h_start);
		
		put(DATA_H_._H_NAME,_h_name);
		
		put(DATA_H_._H_CRASHED,_h_crashed);
		put(DATA_H_._H_RESTING,_h_resting);
		put(DATA_H_._H_IMMOBILE,_h_immobile);

		put(DATA_H_._H_PATH,_h_path);
		put(DATA_H_._H_RGOLD,_h_rgold);
		put(DATA_H_._H_RLIFE,_h_rlife);
		
		put(DATA_H_._H_RANK,rank(_h_gold));
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
	
	
	private boolean b_(Object o)
	{return ((Boolean)o).booleanValue();}
	
	private int[] rank(int[] gold) throws Exception
	{return (int[]) ranking.t(gold);}
	
	private boolean nextToBeer(int[][] tiles, int[] pos) throws Exception
	{return nextToBeer.f(new Object[]{tiles,pos});}
	
	private int distance(int[] p1, int[] p2)
	{return Math.abs(p1[0]-p2[0]) + Math.abs(p1[1]-p2[1]);}
}
