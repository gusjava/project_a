package a.entity.gus06.appli.vindinium.bot.strategy.path.t03.f02.fight;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170923";}

	public static final String ORDER_BEER = "beer";
	public static final String ORDER_TMINE = "tmine";
	public static final String ORDER_ENEMY = "enemy";
	

	public Object t(Object obj) throws Exception
	{
		int[] t = (int[]) obj;
		if(t.length!=8) throw new Exception("Wrong data number: "+t.length);
		
		int d_tmine = t[0];
		int d_beer = t[1];
		int d_enemy = t[2];
		
		int life = t[3];
		int mine = t[4];
		
		int lifeE = t[5];
		int mineE = t[6];
		int rankingE = t[7];
		
		switch(d_enemy){
		case 2:return order_2(d_tmine,d_beer,life,mine,lifeE,mineE,rankingE);
		case 3:return order_3(d_tmine,d_beer,life,mine,lifeE,mineE,rankingE);
		case 4:return order_3(d_tmine,d_beer,life,mine,lifeE,mineE,rankingE);
		default:return order_n(d_tmine,d_beer,life,mine,lifeE,mineE,rankingE);
		}
	}
	
	
	
	private String order_2(int d_tmine, int d_beer, int life, int mine, int lifeE, int mineE, int rankingE)
	{
		// suicidaire
		if(mine==0 && mineE>0) return ORDER_ENEMY;
		
		// coup fatal
		if(lifeE<20 && mineE>0) return ORDER_ENEMY;
		
		
		
		int dlife = life-lifeE;
		boolean top = isTopEnemy(mineE,rankingE);
		
		if(dlife<0)
			return ORDER_BEER;

		if(dlife<10)
		{
			if(mine<2 && top) return ORDER_ENEMY;
			return ORDER_BEER;
		}
		
		if(dlife<20)
		{
			if(mine<3 && top) return ORDER_ENEMY;
			return ORDER_BEER;
		}
		
		if(dlife<30)
		{
			if(mine<5 && mineE>2) return ORDER_ENEMY;
			return ORDER_BEER;
		}

		if(mine<8 && mineE>1) return ORDER_ENEMY;
		if(mine>=8) return ORDER_BEER;
		
		return order_calm(d_tmine,d_beer,life);
	}
	
	
	private String order_3(int d_tmine, int d_beer, int life, int mine, int lifeE, int mineE, int rankingE)
	{
		int dlife = life-lifeE;
		boolean top = isTopEnemy(mineE,rankingE);
		
		if(dlife<0)
			return ORDER_BEER;

		if(dlife<10)
		{
			if(mine==0 && top) return ORDER_ENEMY;
			return order_calm(d_tmine,d_beer,life);
		}
		
		if(dlife<20)
		{
			if(mine<1 && top) return ORDER_ENEMY;
			return order_calm(d_tmine,d_beer,life);
		}
		
		if(dlife<30)
		{
			if(mine<3 && top) return ORDER_ENEMY;
			return order_calm(d_tmine,d_beer,life);
		}

		if(mine>=8) return ORDER_BEER;
		return order_calm(d_tmine,d_beer,life);
	}
	
	
	
	
	private String order_n(int d_tmine, int d_beer, int life, int mine, int lifeE, int mineE, int rankingE)
	{
		boolean top = isTopEnemy(mineE,rankingE);
		int dlife = life-lifeE;
		
		if(dlife>10 && top) return ORDER_ENEMY;
		return order_calm(d_tmine,d_beer,life);
	}
	
	
	
	
	private String order_calm(int d_tmine, int d_beer, int life)
	{
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
	
	private boolean isTopEnemy(int mineE, int rankingE)
	{
		if(rankingE==1 && mineE>3) return true;
		return mineE>6;
	}
}
