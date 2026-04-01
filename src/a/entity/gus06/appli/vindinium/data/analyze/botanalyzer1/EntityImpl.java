package a.entity.gus06.appli.vindinium.data.analyze.botanalyzer1;

import java.util.List;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, P, E {

	public String creationDate() {return "20170923";}

	private Service allEnemies;
	private int[] distance_me0;
	

	public EntityImpl() throws Exception
	{
		allEnemies = Outside.service(this,"gus06.appli.vindinium.bot.tool.searchpath.all.enemy");
		e();
	}
	
	public void e() throws Exception
	{
		distance_me0 = null;
	}


	public void p(Object obj) throws Exception
	{
		if(obj==null) return;
		
		Map data = (Map) obj;
		
		int[][] pos = (int[][]) data.get(DATA_H_._H_POS);
		int[] life = (int[]) data.get(DATA_H_._H_LIFE);
		int me_life = me_life(data);
		
		List paths = (List) allEnemies.t(data);
		
		Object[] me_path = new Object[]{null,null,null,null};
		int[] me_distance = new int[]{0,0,0,0};
		int[] me_lifediff = new int[]{0,0,0,0};
		boolean[] me_approaching = new boolean[]{false,false,false,false};
		boolean[] me_weaker = new boolean[]{false,false,false,false};
		boolean[] me_close = new boolean[]{false,false,false,false};
		
		for(int i=0;i<paths.size();i++)
		{
			int[][] p = (int[][]) paths.get(i);
			int index = findEnemyIndex(pos,p[p.length-1]);
			
			me_path[index] = p;
			me_distance[index] = p.length;
			me_lifediff[index] = life[index] - me_life;
			me_weaker[index] = life[index] < me_life;
			me_close[index] = me_distance[index]==2;
			
			if(distance_me0!=null)
			me_approaching[index] = distance_me0[index] > me_distance[index];
		}
		
		distance_me0 = me_distance;
		
		data.put(DATA_H_._H_ME_PATH,me_path);
		data.put(DATA_H_._H_ME_DISTANCE,me_distance);
		data.put(DATA_H_._H_ME_LIFEDIFF,me_lifediff);
		data.put(DATA_H_._H_ME_APPROACHING,me_approaching);
		data.put(DATA_H_._H_ME_WEAKER,me_weaker);
		data.put(DATA_H_._H_ME_CLOSE,me_close);
	}
	
	
	private int findEnemyIndex(int[][] pos, int[] p) throws Exception
	{
		for(int i=0;i<pos.length;i++)
			if(equals(pos[i],p)) return i;
		throw new Exception("No enemy found for position: "+toString(p));
	}
	
	
	private int me_life(Map data)
	{
		int[] state = (int[]) data.get(DATA_ME_._ME_STATE);
		return state[1];
	}
	
	private boolean equals(int[] p1, int[] p2)
	{return p1[0]==p2[0] && p1[1]==p2[1];}
	
	private String toString(int[] p)
	{return p[0]+" "+p[1];}
}
