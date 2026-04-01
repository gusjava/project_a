package a.entity.gus06.appli.vindinium.bot.strategy.path.t04;

import java.util.Map;
import a.framework.*;

public class EntityImpl extends TT04 implements Entity, T {

	public String creationDate() {return "20170923";}

	private Service nearestBeer;
	private Service nearestBeerSafe;
	private Service nearestTMine;
	
	
	public EntityImpl() throws Exception
	{
		nearestBeer = Outside.service(this,"gus06.appli.vindinium.bot.tool.searchpath.nearest.beer");
		nearestBeerSafe = Outside.service(this,"gus06.appli.vindinium.bot.tool.searchpath.nearest.beer.safe");
		nearestTMine = Outside.service(this,"gus06.appli.vindinium.bot.tool.searchpath.nearest2.tmine");
	}


	public Object t(Object obj) throws Exception
	{return choosePath((Map) obj);}

	
	
	private int[][] choosePath(Map data) throws Exception
	{
		initData(data);
		
		int[][] path_tmine = path_tmine(data);
		int[][] path_beer = path_beer(data);
		int[][] path_beerSafe = path_beerSafe(data);
		
		int d_tmine = pathLength(path_tmine);
		
		boolean isThirsty = me_life<70;
		boolean cannotReachMine = me_life-d_tmine <= HIT;
		
		if(hasValue(_h_me_score1,-1)) // FUITE
		{
			data.put(DATA_BOT_._BOT_STRATEGY,"afraid");
			return path_beerSafe;
		}
		
		int bestScore_index = trueIndex(_h_me_score1_best);
		int bestScore = _h_me_score1[bestScore_index];
		
		if(bestScore==0) // ignore enemies
		{
			if(d_tmine == -1)
			{
				data.put(DATA_BOT_._BOT_STRATEGY,"ignore");
				if(me_resting && !isThirsty) return null;
				return path_beer;
			}
			if(cannotReachMine)
			{
				data.put(DATA_BOT_._BOT_STRATEGY,"weak");
				return path_beer;
			}
			if(isThirsty && me_resting)
			{
				data.put(DATA_BOT_._BOT_STRATEGY,"thirsty");
				return path_beer;
			}
			return path_tmine;
		}

		data.put(DATA_BOT_._BOT_STRATEGY,"attack "+bestScore_index);
		return (int[][]) _h_me_path[bestScore_index];
	}
	


	private int[][] path_beer(Map data) throws Exception
	{return (int[][]) nearestBeer.t(data);}
	
	private int[][] path_beerSafe(Map data) throws Exception
	{return (int[][]) nearestBeerSafe.t(data);}
	
	private int[][] path_tmine(Map data) throws Exception
	{return (int[][]) nearestTMine.t(data);}
}
