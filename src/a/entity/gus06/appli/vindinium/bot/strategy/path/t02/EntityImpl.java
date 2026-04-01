package a.entity.gus06.appli.vindinium.bot.strategy.path.t02;

import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170923";}

	public static final String ORDER_BEER = "beer";
	public static final String ORDER_TMINE = "tmine";
	public static final String ORDER_ENEMY = "enemy";
	

	private Service nearestBeer;
	private Service nearestTMine;
	private Service nearestEnemy;
	private Service getOrder;
	

	public EntityImpl() throws Exception
	{
		nearestBeer = Outside.service(this,"gus06.appli.vindinium.bot.tool.searchpath.nearest.beer");
		nearestTMine = Outside.service(this,"gus06.appli.vindinium.bot.tool.searchpath.nearest.tmine");
		nearestEnemy = Outside.service(this,"gus06.appli.vindinium.bot.tool.searchpath.nearest.enemy");
		getOrder = Outside.service(this,"gus06.appli.vindinium.bot.strategy.path.t02.f01");
	}

	public Object t(Object obj) throws Exception
	{
		Map data = (Map) obj;
		return choosePath(data);
	}
	
	private int[][] choosePath(Map data) throws Exception
	{
		int[][] path_tmine = path_tmine(data);
		int[][] path_beer = path_beer(data);
		int[][] path_enemy = path_enemy(data);
		
		int d_tmine = pathLength(path_tmine);
		int d_beer = pathLength(path_beer);
		int d_enemy = pathLength(path_enemy);
		
		int life = life(data);
		int lifeE = lifeE(data,path_enemy);
		
		
		String order = getOrder(d_tmine,d_beer,d_enemy,life,lifeE);
		
		if(order==null) return null;
		if(order.equals(ORDER_BEER)) return path_beer;
		if(order.equals(ORDER_TMINE)) return path_tmine;
		if(order.equals(ORDER_ENEMY)) return path_enemy;
		
		throw new Exception("Unknown order: "+order);
	}
	
	
	
	private String getOrder(int d_tmine, int d_beer, int d_enemy, int life, int lifeE) throws Exception
	{return (String) getOrder.t(new int[]{d_tmine,d_beer,d_enemy,life,lifeE});}
	
	private int pathLength(int[][] path)
	{return path!=null?path.length:-1;}
	
	
	private int[][] path_beer(Map data) throws Exception
	{return (int[][]) nearestBeer.t(data);}
	
	private int[][] path_tmine(Map data) throws Exception
	{return (int[][]) nearestTMine.t(data);}
	
	private int[][] path_enemy(Map data) throws Exception
	{return (int[][]) nearestEnemy.t(data);}
	
	
	private int life(Map data)
	{return ((int[]) data.get(DATA_ME_._ME_STATE))[1];}
	
	
	
	private int lifeE(Map data, int[][] path)
	{
		if(path==null) return -1;
		int[] pos = path[path.length-1];
		
		int[][] _h_pos = (int[][]) data.get(DATA_H_._H_POS);
		int[] _h_life = (int[]) data.get(DATA_H_._H_LIFE);
		
		for(int i=0;i<_h_life.length;i++)	
		if(equals(_h_pos[i],pos)) return _h_life[i];
		return -1;
	}
	
	
	private boolean equals(int[] p1, int[] p2)
	{return p1!=null && p2!=null && p1[0]==p2[0] && p1[1]==p2[1];}
}
