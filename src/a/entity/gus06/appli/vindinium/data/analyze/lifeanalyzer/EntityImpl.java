package a.entity.gus06.appli.vindinium.data.analyze.lifeanalyzer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, P, E {

	public String creationDate() {return "20170923";}

	public static final int EPSILON = 3;
	
	
	private int[] pkills;
	private int[] pdrinks;
	private int[] phits;
	
	private List[] rlife;
	private int[] life0;


	public EntityImpl() throws Exception
	{e();}
	
	
	public void e() throws Exception
	{
		rlife = new List[4];
		for(int i=0;i<4;i++) rlife[i] = new ArrayList();
		
		pkills = new int[]{0,0,0,0};
		pdrinks = new int[]{0,0,0,0};
		phits = new int[]{0,0,0,0};
		
		life0 = new int[]{100,100,100,100};
	}


	public void p(Object obj) throws Exception
	{
		if(obj==null) return;
		Map data = (Map) obj;
		
		int[] life = (int[]) data.get(DATA_H_._H_LIFE);
		int[] mine = (int[]) data.get(DATA_H_._H_MINE);
		
		boolean[] vulnerable = new boolean[4];
		boolean[] suicidal = new boolean[4];
		
		for(int i=0;i<4;i++)
		{
			List l = rlife[i];
			l.add(Integer.valueOf(life[i]));
			
			int dlife = life[i]-life0[i];
			life0[i] = life[i];
			
			if(dlife > LIFE.MAX-LIFE.HIT-EPSILON)
				pkills[i]++;
			else if(equals_(dlife,LIFE.DRINK))
				pdrinks[i]++;
			else if(equals_(dlife,-LIFE.HIT))
				phits[i]++;
			else if(equals_(dlife,LIFE.DRINK-LIFE.HIT))
			{
				pdrinks[i]++;
				phits[i]++;
			}
			
			vulnerable[i] = life[i]<=LIFE.HIT;
			suicidal[i] = vulnerable[i] && mine[i]==0;
		}
		
		data.put(DATA_H_._H_PKILLED,pkills);
		data.put(DATA_H_._H_PDRINK,pdrinks);
		data.put(DATA_H_._H_PHIT,phits);
		data.put(DATA_H_._H_RLIFE,rlife);
		data.put(DATA_H_._H_VULNERABLE,vulnerable);
		data.put(DATA_H_._H_SUICIDAL,suicidal);
	}
	
	
	private boolean equals_(int d, int value)
	{return Math.abs(d-value)<EPSILON;}
}
