package a.entity.gus06.appli.vindinium.data.analyze.botscore1;

import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, P, E {

	public String creationDate() {return "20170923";}

	public EntityImpl() throws Exception
	{}
	
	public void e() throws Exception
	{}
	
	private int me_index;
	private boolean me_resting;
	private boolean me_vulnerable;
	private int me_close_number;
	private boolean me_close;
	private boolean me_close2;
	private boolean me_suicidal;
	
	private boolean[] _h_resting;
	private boolean[] _h_vulnerable;
	private boolean[] _h_suicidal;
	private boolean[] _h_me_close;
	private boolean[] _h_me_weaker;
	private boolean[] _h_me_approaching;
	
	private int[] _h_mine;
	private int[] _h_away;
	private int[] _h_me_distance;
	private int[] _h_me_lifediff;
	

	public void p(Object obj) throws Exception
	{
		if(obj==null) return;
		
		Map data = (Map) obj;
		
		_h_resting = (boolean[]) data.get(DATA_H_._H_RESTING);
		_h_vulnerable = (boolean[]) data.get(DATA_H_._H_VULNERABLE);
		_h_suicidal = (boolean[]) data.get(DATA_H_._H_SUICIDAL);
		_h_me_close = (boolean[]) data.get(DATA_H_._H_ME_CLOSE);
		_h_me_weaker = (boolean[]) data.get(DATA_H_._H_ME_WEAKER);
		_h_me_approaching = (boolean[]) data.get(DATA_H_._H_ME_APPROACHING);
		
		_h_mine = (int[]) data.get(DATA_H_._H_MINE);
		_h_away = (int[]) data.get(DATA_H_._H_AWAY);
		_h_me_distance = (int[]) data.get(DATA_H_._H_ME_DISTANCE);
		_h_me_lifediff = (int[]) data.get(DATA_H_._H_ME_LIFEDIFF);
		
		int[] _me_state = (int[]) data.get(DATA_ME_._ME_STATE);
		me_index = _me_state[0]-1;
		
		me_resting = _h_resting[me_index];
		me_vulnerable = _h_vulnerable[me_index];
		me_suicidal = _h_suicidal[me_index];
		
		me_close_number = numberTrue(_h_me_close);
		me_close = me_close_number>0;
		me_close2 = me_close_number>1;
		
		
		int[] score = new int[4];
		for(int i=0;i<4;i++) score[i] = buildScore(i);
		
		boolean[] score_best = new boolean[4];
		for(int i=0;i<4;i++) score_best[i] = isScoreBest(i,score);
		
		data.put(DATA_H_._H_ME_SCORE1,score);
		data.put(DATA_H_._H_ME_SCORE1_BEST,score_best);
	}
	
	
	private int buildScore(int index)
	{
		if(index==me_index) return 0;
		
		if(_h_me_close[index])
			return buildScore_close(index);
		return buildScore_distant(index);
	}
	
	
	private boolean isScoreBest(int index, int[] score)
	{
		for(int i=0;i<4;i++)
			if(score[i]>score[index]) return false;
		return true;
	}
	

	private int buildScore_close(int index)
	{
		boolean h_resting = _h_resting[index];
		boolean h_vulnerable = _h_vulnerable[index];
		boolean h_weaker = _h_me_weaker[index];
		boolean h_suicidal = _h_suicidal[index];
		int h_mine = _h_mine[index];
		int h_away = _h_away[index];
		
		// situation 1 : h_resting & me_resting
		if(h_resting && me_resting)
		{
			if(me_vulnerable && me_close2) return 0;
			if(!h_vulnerable) return 0;
				
			return 1000 + h_mine;
		}
		
		// situation 2 : !h_resting & me_resting
		if(!h_resting && me_resting)
		{
			if(me_vulnerable && me_close2) return 0;
			if(me_vulnerable && !h_vulnerable) return 0;
				
			return 1000 + h_mine;
		}
		
		// situation 3 : h_resting & !me_resting
		if(h_resting && !me_resting)
		{
			if(me_suicidal && !h_suicidal) return h_mine;
			return -1;
		}
		
		// situation 4 : !h_resting & !me_resting
		if(!h_resting && !me_resting)
		{
			if(me_suicidal && !h_suicidal) return 100 + h_mine;
			if(h_weaker && h_away>2) return 100 + h_mine;
			return -1;
		}
		return 0;
	}
	
	
	
	private int buildScore_distant(int index)
	{
		boolean h_resting = _h_resting[index];
		boolean h_vulnerable = _h_vulnerable[index];
		boolean h_weaker = _h_me_weaker[index];
		boolean h_suicidal = _h_suicidal[index];
		boolean h_approaching = _h_me_approaching[index];
		
		int h_mine = _h_mine[index];
		int h_away = _h_away[index];
		int h_lifediff = _h_me_lifediff[index];
		int h_distance = _h_me_distance[index];
		
		if(me_vulnerable) return 0;
		
		
		if(!h_resting && h_approaching && h_lifediff>h_distance)
		return h_mine;
		
		return 0;
	}

	
	protected int numberTrue(boolean[] b_)
	{
		int n = 0;
		for(boolean b:b_) if(b) n++;
		return n;
	}
}
