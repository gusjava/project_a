package a.entity.gus06.appli.vindinium.bot.strategy.path.t03;

import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170923";}

	
	public static final String ORDER_BEER = "beer";
	public static final String ORDER_TMINE = "tmine";
	public static final String ORDER_ENEMY = "enemy";
	
	public static final String STRATEGY_CALM = "t03-calm";
	public static final String STRATEGY_FIGHT = "t03-fight";
	
	public static final int FIGHT_AREA = 7;


	
	private Service nearestBeer;
	private Service nearestBeerSafe;
	private Service nearestTMine;
	private Service nearestEnemy;
	private Service getOrder_calm;
	private Service getOrder_fight;
	
	
	public EntityImpl() throws Exception
	{
		nearestBeer = Outside.service(this,"gus06.appli.vindinium.bot.tool.searchpath.nearest.beer");
		nearestBeerSafe = Outside.service(this,"gus06.appli.vindinium.bot.tool.searchpath.nearest.beer.safe");
		nearestTMine = Outside.service(this,"gus06.appli.vindinium.bot.tool.searchpath.nearest2.tmine");
		nearestEnemy = Outside.service(this,"gus06.appli.vindinium.bot.tool.searchpath.nearest.enemy");
		getOrder_calm = Outside.service(this,"gus06.appli.vindinium.bot.strategy.path.t03.f01.calm");
		getOrder_fight = Outside.service(this,"gus06.appli.vindinium.bot.strategy.path.t03.f02.fight");
	}


	public Object t(Object obj) throws Exception
	{return choosePath((Map) obj);}
	
	
	private int[][] choosePath(Map data) throws Exception
	{
		int[][] path_tmine = path_tmine(data);
		int[][] path_beer = path_beerSafe(data);
		int[][] path_enemy = path_enemy(data);
		
		if(path_beer==null)
			path_beer = path_beer(data);
		
		int d_tmine = pathLength(path_tmine);
		int d_beer = pathLength(path_beer);
		int d_enemy = pathLength(path_enemy);
		
		int life = life(data);
		int mine = mine(data);
		
		int[] infosE = enemyInfos(data,path_enemy);
		if(infosE==null) throw new Exception("Invalid enemy path found inside strategy EntityImpl");
		
		int lifeE = infosE[0];
		int mineE = infosE[1];
		int rankingE = infosE[2];
		
		String strategy = getStrategy(d_enemy);
		data.put(DATA_BOT_._BOT_STRATEGY,strategy);
		
		String order = getOrder(d_tmine,d_beer,d_enemy,life,mine,lifeE,mineE,rankingE);
		
		if(order==null) return null;
		if(order.equals(ORDER_BEER)) return path_beer;
		if(order.equals(ORDER_TMINE)) return path_tmine;
		if(order.equals(ORDER_ENEMY)) return path_enemy;
		
		throw new Exception("Unknown order: "+order);
	}
	
	
	private String getStrategy(int d_enemy)
	{
		return d_enemy>FIGHT_AREA?STRATEGY_CALM:STRATEGY_FIGHT;
	}
	
	private String getOrder(int d_tmine, int d_beer, int d_enemy, int life, int mine, int lifeE, int mineE, int rankingE) throws Exception
	{
		if(d_enemy>FIGHT_AREA) return getOrder_calm(d_tmine,d_beer,life);
		return getOrder_fight(d_tmine,d_beer,d_enemy,life,mine,lifeE,mineE,rankingE);
	}
	
	
	private String getOrder_calm(int d_tmine, int d_beer, int life) throws Exception
	{return (String) getOrder_calm.t(new int[]{d_tmine,d_beer,life});}
	
	private String getOrder_fight(int d_tmine, int d_beer, int d_enemy, int life, int mine, int lifeE, int mineE, int rankingE) throws Exception
	{return (String) getOrder_fight.t(new int[]{d_tmine,d_beer,d_enemy,life,mine,lifeE,mineE,rankingE});}
	
	
	
	private int pathLength(int[][] path)
	{return path!=null?path.length:-1;}
	
	private int[][] path_beer(Map data) throws Exception
	{return (int[][]) nearestBeer.t(data);}
	
	private int[][] path_beerSafe(Map data) throws Exception
	{return (int[][]) nearestBeerSafe.t(data);}
	
	private int[][] path_tmine(Map data) throws Exception
	{return (int[][]) nearestTMine.t(data);}
	
	private int[][] path_enemy(Map data) throws Exception
	{return (int[][]) nearestEnemy.t(data);}
	
	
	private int life(Map data)
	{return ((int[]) data.get(DATA_ME_._ME_STATE))[1];}
	
	private int mine(Map data)
	{return ((int[]) data.get(DATA_ME_._ME_STATE))[3];}
	
	
	
	private int[] enemyInfos(Map data, int[][] path)
	{
		if(path==null || path.length<2) return null;
		
		int[] pos = path[path.length-1];
		int index = findEnemyIndex(data,pos);
		if(index==-1) return null;
		
		
		int[] _h_life = (int[]) data.get(DATA_H_._H_LIFE);
		int[] _h_mine = (int[]) data.get(DATA_H_._H_MINE);
		int[] _h_rank = (int[]) data.get(DATA_H_._H_RANK);
		
		return new int[]{
				_h_life[index],
				_h_mine[index],
				_h_rank[index]
			};
	}
	
	
	private int findEnemyIndex(Map data, int[] pos)
	{
		int[][] _h_pos = (int[][]) data.get(DATA_H_._H_POS);
		for(int i=0;i<_h_pos.length;i++)	
			if(equals(_h_pos[i],pos)) return i;
		return -1;
	}
	
	private boolean equals(int[] p1, int[] p2)
	{return p1!=null && p2!=null && p1[0]==p2[0] && p1[1]==p2[1];}
}
