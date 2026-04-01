package a.entity.gus06.appli.vindinium.data.analyze.goldanalyzer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, P, E {

	public String creationDate() {return "20170923";}


	private Service ranking;
	
	private List[] rgold;

	public EntityImpl() throws Exception
	{
		ranking = Outside.service(this,"gus06.math.tabint.ranking");
		e();
	}
	
	public void e() throws Exception
	{
		rgold = new List[4];
		for(int i=0;i<4;i++) rgold[i] = new ArrayList();
	}
	

	public void p(Object obj) throws Exception
	{
		if(obj==null) return;
		Map data = (Map) obj;
		
		int[] gold = (int[]) data.get(DATA_H_._H_GOLD);
		int[] mine = (int[]) data.get(DATA_H_._H_MINE);
		int[] drink = (int[]) data.get(DATA_H_._H_PDRINK);
		int[] turns = (int[]) data.get(DATA_._TURNS);
		
		int turn_done = turns[0];
		int turn_left = turns[2];
		
		int[] fgold = new int[4];
		
		for(int i=0;i<4;i++)
		{
			List l = rgold[i];
			l.add(Integer.valueOf(gold[i]));
			
			int drink_left = estimateDrinkLeft(drink[i],turn_done,turn_left);
			fgold[i] = gold[i] + turn_left*mine[i] - drink_left*2;
			if(fgold[i]<0) fgold[i]=0;
		}
		
		data.put(DATA_H_._H_RGOLD,rgold);
		data.put(DATA_H_._H_FGOLD,fgold);
		data.put(DATA_H_._H_FRANK,rank(fgold));
	}
	
	
	private int estimateDrinkLeft(int drink_done, int turn_done, int turn_left)
	{
		double rate = (double) drink_done / (double) turn_done;
		return (int) (rate * (double) turn_left);
	}
	
	
	private int[] rank(int[] gold) throws Exception
	{return (int[]) ranking.t(gold);}
}