package a.entity.gus06.appli.vindinium.data.retrievedata.format.turns;

import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20170923";}

	
	private Map data;
	
	private void put(String key, Object value)
	{data.put(key,value);}
	
	
	public void p(Object obj) throws Exception
	{
		if(obj==null) return;
		data = (Map) obj;
		
		Map game = (Map) data.get(DATA.K_GAME);
		
		int turn = i_(game.get(DATA.G_TURN))/4;
		int turnMax = i_(game.get(DATA.G_MAXTURNS))/4;
		int turnLeft = turnMax-turn;
		
		put(DATA_._TURNS,new int[]{turn,turnMax,turnLeft});
	}
	
	private int i_(Object s)
	{
		if(s==null) return -1;
		return Integer.parseInt((String) s);
	}
}
