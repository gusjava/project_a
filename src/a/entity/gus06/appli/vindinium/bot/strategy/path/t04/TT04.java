package a.entity.gus06.appli.vindinium.bot.strategy.path.t04;

import java.util.List;
import java.util.Map;


public class TT04 {


	public static final int HIT = 20;
	
	
	
	protected int[] _h_elo;
	protected int[] _h_rank;
	protected int[] _h_gold;
	protected int[] _h_life;
	protected int[] _h_mine;
	protected int[] _h_away;
	
	protected int[] _h_pkilled;
	protected int[] _h_pdrink;
	protected int[] _h_pmine;
	protected int[] _h_phit;
	
	protected int[] _h_fgold;
	protected int[] _h_frank;
	
	protected int[][] _h_pos;
	protected int[][] _h_pos0;
	protected int[][] _h_start;
	
	protected String[] _h_name;
	
	protected boolean[] _h_crashed;
	protected boolean[] _h_resting;
	protected boolean[] _h_immobile;
	protected boolean[] _h_vulnerable;
	protected boolean[] _h_suicidal;
	
	protected List[] _h_path;
	protected List[] _h_rgold;
	protected List[] _h_rlife;
	
	protected Object[] _h_me_path;
	protected int[] _h_me_distance;
	protected int[] _h_me_lifediff;
	protected int[] _h_me_score1;
	protected boolean[] _h_me_approaching;
	protected boolean[] _h_me_weaker;
	protected boolean[] _h_me_close;
	protected boolean[] _h_me_score1_best;
	
	protected int[] _turns;
	protected int[][] _board;
	protected boolean[][] _maze;
	protected List _air;
	protected List _wall;
	protected List _beer;
	protected List _mine;
	protected List _mine_me;
	protected List _mine_free;
	protected List _mine_target;
	protected List _enemy;
	
	protected int[] _me_state;
	protected String _me_name;
	protected int[] _me_pos;
	
	protected int me_id;
	protected int me_index;
	protected int me_elo;
	protected int me_rank;
	protected int me_gold;
	protected int me_life;
	protected int me_mine;
	protected int me_away;
	
	protected int me_pkilled;
	protected int me_pdrink;
	protected int me_pmine;
	protected int me_phit;
	
	protected int me_fgold;
	protected int[] me_pos;
	protected int[] me_pos0;
	protected int[] me_start;
	
	protected String me_name;
	
	protected boolean me_crashed;
	protected boolean me_resting;
	protected boolean me_immobile;
	
	protected List me_path;
	protected List me_rgold;
	protected List me_rlife;
	
	protected int turns_done;
	protected int turns_total;
	protected int turns_left;
	
	protected int board_size;
	protected int air_number;
	protected int wall_number;
	protected int beer_number;
	protected int mine_number;
	protected int mine_me_number;
	protected int mine_free_number;
	protected int mine_target_number;
	protected int enemy_number;
	protected int close_number;
	
	
	protected boolean me_noTarget;
	protected boolean me_noMine;
	protected boolean me_atHome;
	protected boolean me_close;
	protected boolean me_close2;
	protected boolean me_vulnerable;
	protected boolean me_suicidal;
	protected boolean me_fgold_best;
	
	protected boolean all_immobile;
	
	protected double enemy_distance_avg;
	protected double enemy_distance_min;
	
	
	

	
	
	
	
	protected void initData(Map data)
	{
		// DATA_H_
		
		_h_elo = (int[]) data.get(DATA_H_._H_ELO);
		_h_rank = (int[]) data.get(DATA_H_._H_RANK);
		_h_gold = (int[]) data.get(DATA_H_._H_GOLD);
		_h_life = (int[]) data.get(DATA_H_._H_LIFE);
		_h_mine = (int[]) data.get(DATA_H_._H_MINE);
		_h_away = (int[]) data.get(DATA_H_._H_AWAY);
		
		_h_pkilled = (int[]) data.get(DATA_H_._H_PKILLED);
		_h_pdrink = (int[]) data.get(DATA_H_._H_PDRINK);
		_h_pmine = (int[]) data.get(DATA_H_._H_PMINE);
		_h_phit = (int[]) data.get(DATA_H_._H_PHIT);
		
		_h_fgold = (int[]) data.get(DATA_H_._H_FGOLD);
		_h_frank = (int[]) data.get(DATA_H_._H_FRANK);
		
		_h_pos = (int[][]) data.get(DATA_H_._H_POS);
		_h_pos0 = (int[][]) data.get(DATA_H_._H_POS0);
		_h_start = (int[][]) data.get(DATA_H_._H_START);
		
		_h_name = (String[]) data.get(DATA_H_._H_NAME);
		
		_h_crashed = (boolean[]) data.get(DATA_H_._H_CRASHED);
		_h_resting = (boolean[]) data.get(DATA_H_._H_RESTING);
		_h_immobile = (boolean[]) data.get(DATA_H_._H_IMMOBILE);
		_h_vulnerable = (boolean[]) data.get(DATA_H_._H_VULNERABLE);
		_h_suicidal = (boolean[]) data.get(DATA_H_._H_SUICIDAL);
		
		_h_path = (List[]) data.get(DATA_H_._H_PATH);
		_h_rgold = (List[]) data.get(DATA_H_._H_RGOLD);
		_h_rlife = (List[]) data.get(DATA_H_._H_RLIFE);
		
		_h_me_path = (Object[]) data.get(DATA_H_._H_ME_PATH);
		_h_me_distance = (int[]) data.get(DATA_H_._H_ME_DISTANCE);
		_h_me_lifediff = (int[]) data.get(DATA_H_._H_ME_LIFEDIFF);
		_h_me_score1 = (int[]) data.get(DATA_H_._H_ME_SCORE1);
		_h_me_approaching = (boolean[]) data.get(DATA_H_._H_ME_APPROACHING);
		_h_me_weaker = (boolean[]) data.get(DATA_H_._H_ME_WEAKER);
		_h_me_close = (boolean[]) data.get(DATA_H_._H_ME_CLOSE);
		_h_me_score1_best = (boolean[]) data.get(DATA_H_._H_ME_SCORE1_BEST);
		
		// DATA_
		
		_turns = (int[]) data.get(DATA_._TURNS);
		_board = (int[][]) data.get(DATA_._BOARD);
		_maze = (boolean[][]) data.get(DATA_._MAZE);
		_air = (List) data.get(DATA_._AIR);
		_wall = (List) data.get(DATA_._WALL);
		_beer = (List) data.get(DATA_._BEER);
		_mine = (List) data.get(DATA_._MINE);
		_mine_me = (List) data.get(DATA_._MINE_ME);
		_mine_free = (List) data.get(DATA_._MINE_FREE);
		_mine_target = (List) data.get(DATA_._MINE_TARGET);
		_enemy = (List) data.get(DATA_._ENEMY);
		
		
		// DATA_ME_
		
		_me_state = (int[]) data.get(DATA_ME_._ME_STATE);
		_me_name = (String) data.get(DATA_ME_._ME_NAME);
		_me_pos = (int[]) data.get(DATA_ME_._ME_POS);
		
		
		
		me_id = _me_state[0];
		me_index = me_id-1;
		
		me_elo = _h_elo[me_index];
		me_rank = _h_rank[me_index];
		me_gold = _h_gold[me_index];
		me_life = _h_life[me_index];
		me_mine = _h_mine[me_index];
		me_away = _h_away[me_index];
		
		me_pkilled = _h_pkilled[me_index];
		me_pdrink = _h_pdrink[me_index];
		me_pmine = _h_pmine[me_index];
		me_phit = _h_phit[me_index];
		
		me_fgold = _h_fgold[me_index];
		me_pos = _h_pos[me_index];
		me_pos0 = _h_pos0[me_index];
		me_start = _h_start[me_index];
		
		me_name = _h_name[me_index];
		
		me_crashed = _h_crashed[me_index];//=false
		me_resting = _h_resting[me_index];
		me_immobile = _h_immobile[me_index];
		me_vulnerable = _h_vulnerable[me_index];
		me_suicidal = _h_suicidal[me_index];
		
		me_path = _h_path[me_index];
		me_rgold = _h_rgold[me_index];
		me_rlife = _h_rlife[me_index];
		
		turns_done = _turns[0];
		turns_total = _turns[1];
		turns_left = _turns[2];
		
		board_size = _board.length;
		air_number = _air.size();
		wall_number = _wall.size();
		beer_number = _beer.size(); //=4
		mine_number = _mine.size();
		mine_me_number = _mine_me.size();
		mine_free_number = _mine_free.size();
		mine_target_number = _mine_target.size();
		enemy_number = _enemy.size();//=3
		close_number = numberTrue(_h_me_close);
		
		me_noTarget = mine_target_number==0;
		me_noMine = me_mine==0;
		me_atHome = me_away==0;
		me_close = close_number>0;
		me_close2 = close_number>1;
		me_fgold_best = max(_h_fgold)==me_fgold;
		
		all_immobile = allTrue(_h_immobile);
		
		enemy_distance_avg = avg(_h_me_distance);
		enemy_distance_min = min(_h_me_distance);
	}
	
	
	

	
	protected int pathLength(int[][] path)
	{return path!=null?path.length:-1;}
	
	
	
	
	protected boolean allTrue(boolean[] b_)
	{
		for(boolean b:b_) if(!b) return false;
		return true;
	}
	
	
	protected int numberTrue(boolean[] b_)
	{
		int n = 0;
		for(boolean b:b_) if(b) n++;
		return n;
	}
	
	
	protected int trueIndex(boolean[] b_)
	{
		for(int i=0;i<b_.length;i++) if(b_[i]) return i;
		return -1;
	}
	
	
	
	
	
	protected boolean hasValue(int[] t_, int value)
	{
		for(int t:t_) if(t==value) return true;
		return false;
	}
	
	
	
	protected int max(int[] t_)
	{
		int max = Integer.MIN_VALUE;
		for(int t:t_) if(t>max) max = t;
		return max;
	}
	
	
	protected int min(int[] t_)
	{
		int min = Integer.MAX_VALUE;
		for(int t:t_) if(t<min) min = t;
		return min;
	}
	
	
	protected double avg(int[] t_)
	{
		int sum = 0;
		for(int t:t_) sum+=t;
		return sum/t_.length;
	}
	
	
	protected int indexForMax(int[] t_)
	{
		int max = Integer.MIN_VALUE;
		int index = -1;
		for(int i=0;i<t_.length;i++) if(t_[i]>max) {max = t_[i];index = i;}
		return index;
	}
	
	
	protected int indexForMin(int[] t_)
	{
		int min = Integer.MAX_VALUE;
		int index = -1;
		for(int i=0;i<t_.length;i++) if(t_[i]<min) {min = t_[i];index = i;}
		return index;
	}
	
	
	
	
	protected int[] distances(List paths)
	{
		int[] d = new int[paths.size()];
		for(int i=0;i<paths.size();i++)
		{
			int[][] path = (int[][]) paths.get(i);
			d[i] = path.length;
		}
		return d;
	}
	
  
	
	
	protected boolean equals(int[] p1, int[] p2)
	{return p1!=null && p2!=null && p1[0]==p2[0] && p1[1]==p2[1];}
}
