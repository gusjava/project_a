package a.entity.gus06.appli.vindinium.bot.strategy.path.t02.f01;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170923";}
	
	public static final String ORDER_BEER = "beer";
	public static final String ORDER_TMINE = "tmine";
	public static final String ORDER_ENEMY = "enemy";
	

	public Object t(Object obj) throws Exception
	{
		int[] t = (int[]) obj;
		if(t.length!=5) throw new Exception("Wrong data number: "+t.length);
		
		int d_tmine = t[0];
		int d_beer = t[1];
		int d_enemy = t[2];
		
		int life = t[3];
		int lifeE = t[4];
		
		
		boolean weakEnemy = lifeE < 20 || lifeE < life - 50;
		
		if(weakEnemy && d_enemy == 2)
		{
			return ORDER_ENEMY;
		}
		
		if(d_tmine == -1)
		{
			if(d_beer == 2 && life > 80) return null;
			return ORDER_BEER;
		}

		if(life-d_tmine <= 20) return ORDER_BEER;

		
		if(life>70 && life <=80) return orderFor_80_70(d_tmine,d_beer);
		if(life>60 && life <=70) return orderFor_70_60(d_tmine,d_beer);
		if(life>50 && life <=60) return orderFor_60_50(d_tmine,d_beer);
		if(life>40 && life <=50) return orderFor_50_40(d_tmine,d_beer);
		if(life>30 && life <=40) return orderFor_40_30(d_tmine,d_beer);
		if(life>25 && life <=30) return orderFor_30_25(d_tmine,d_beer);
		if(life>20 && life <=25) return orderFor_25_20(d_tmine,d_beer);
		
		return ORDER_TMINE;
	}
	
	
	private String orderFor_80_70(int d_tmine, int d_beer)
	{
		return d_beer == 2 && d_tmine >= 5 ? ORDER_BEER : ORDER_TMINE;
	}
	
	private String orderFor_70_60(int d_tmine, int d_beer)
	{
		return d_beer == 2 ? ORDER_BEER : ORDER_TMINE;
	}
	
	private String orderFor_60_50(int d_tmine, int d_beer)
	{
		return d_tmine + 5 < d_beer ? ORDER_TMINE : ORDER_BEER;
	}
	
	private String orderFor_50_40(int d_tmine, int d_beer)
	{
		return d_tmine + 3 < d_beer ? ORDER_TMINE : ORDER_BEER;
	}
	
	private String orderFor_40_30(int d_tmine, int d_beer)
	{
		return d_tmine < d_beer ? ORDER_TMINE : ORDER_BEER;
	}
	
	private String orderFor_30_25(int d_tmine, int d_beer)
	{
		return d_tmine <= 3 && d_beer <= 4 ? ORDER_TMINE : ORDER_BEER;
	}
	
	private String orderFor_25_20(int d_tmine, int d_beer)
	{
		return d_tmine == 2 && d_beer <= 3 ? ORDER_TMINE : ORDER_BEER;
	}
}
