package a.entity.gus06.appli.vindinium.data.retrievedata.format.me;

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
		
		Map me = (Map) data.get(DATA.K_HERO);
		
		int me_id = i_(me.get(DATA.H_ID));
		int _me_life = i_(me.get(DATA.H_LIFE));
		int _me_gold = i_(me.get(DATA.H_GOLD));
		int _me_mine = i_(me.get(DATA.H_MINECOUNT));
		int _me_elo = i_(me.get(DATA.H_ELO));
		int[] me_pos = ii_(me.get(DATA.H_POS));
		String me_name = (String) me.get(DATA.H_NAME);
		
		put(DATA_ME_._ME_STATE,new int[]{me_id,_me_life,_me_gold,_me_mine,_me_elo});
		put(DATA_ME_._ME_POS,me_pos);
		put(DATA_ME_._ME_NAME,me_name);
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
}
