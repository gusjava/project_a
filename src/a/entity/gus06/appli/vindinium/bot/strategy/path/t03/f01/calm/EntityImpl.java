package a.entity.gus06.appli.vindinium.bot.strategy.path.t03.f01.calm;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170923";}

	public static final String ORDER_BEER = "beer";
	public static final String ORDER_TMINE = "tmine";
	

	public Object t(Object obj) throws Exception
	{
		int[] t = (int[]) obj;
		if(t.length!=3) throw new Exception("Wrong data number: "+t.length);
		
		int d_tmine = t[0];
		int d_beer = t[1];
		int life = t[2];
		
		
		if(d_tmine == -1)
		{
			if(d_beer==2 && !isThirsty(life)) return null;
			return ORDER_BEER;
		}

		if(life-d_tmine<=20) return ORDER_BEER;
		if(isThirsty(life) && d_beer==2) return ORDER_BEER;
		
		return ORDER_TMINE;
	}
	
	
	private boolean isThirsty(int life)
	{return life<70;}
}
