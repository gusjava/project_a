package a.entity.gus06.appli.vindinium.bot.strategy.path.t01;

import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170923";}


	private Service nearestBeer;
	private Service nearestTMine;

	public EntityImpl() throws Exception
	{
		nearestBeer = Outside.service(this,"gus06.appli.vindinium.bot.tool.searchpath.nearest.beer");
		nearestTMine = Outside.service(this,"gus06.appli.vindinium.bot.tool.searchpath.nearest.tmine");
	}

	public Object t(Object obj) throws Exception
	{
		Map data = (Map) obj;
		Map params = (Map) data.get(DATA_BOT_._BOT_STRATEGYPARAMS);
		int limit = i_(params.get("limit"));
		return choosePath(data,limit);
	}
	
	
	private int[][] choosePath(Map data, int limit) throws Exception
	{
		if(life(data) <= limit) return path_beer(data);
		
		int[][] path_tmine = path_tmine(data);
		if(path_tmine==null) return path_beer(data);
		return path_tmine;
	}
	
	
	private int[][] path_beer(Map data) throws Exception
	{return (int[][]) nearestBeer.t(data);}
	
	private int[][] path_tmine(Map data) throws Exception
	{return (int[][]) nearestTMine.t(data);}
	
	private int life(Map data)
	{return ((int[]) data.get(DATA_ME_._ME_STATE))[1];}
	
	private int i_(Object o)
	{return Integer.parseInt((String)o);}
}
