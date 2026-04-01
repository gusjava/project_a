package a.entity.gus06.appli.vindinium.gui.gameview.board.buildimage.drawbotfeatures;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.Map;
import javax.swing.Icon;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20170923";}


	public static final int GAP = 100;
	public static final int HIT = 20;
	public static final int LIFEBAR_THICKNESS = 15;
	public static final Font SCORE_FONT = new Font("Arial",Font.BOLD,30);
	
	public static final String ICONID_CRASHED = "GAME_vindinium_crashed";
	public static final String ICONID_APPROACH = "GAME_vindinium_approaching";
	public static final String ICONID_CLOSE = "GAME_vindinium_close";
	public static final String ICONID_IMMOBILE = "GAME_vindinium_immobile";
	public static final String ICONID_WEAKER = "GAME_vindinium_weaker";
	public static final String ICONID_RANK_ = "GAME_vindinium_rank";
	
	public static final boolean DRAW_CLOSE = false;
	public static final boolean DRAW_APPROACHING = false;
	public static final boolean DRAW_IMMOBILE = false;
	public static final boolean DRAW_WEAKER = true;
	public static final boolean DRAW_FRANK = false;
	public static final boolean DRAW_SCORE = true;
	

	
	private Service ip;
	
	public EntityImpl() throws Exception
	{
		ip = Outside.service(this,"gus06.icon.provider");
	}
	

	public void p(Object obj) throws Exception
	{
		Object[] t = (Object[]) obj;
		if(t.length!=2) throw new Exception("Wrong data number: "+t.length);
		
		Graphics2D g = (Graphics2D) t[0];
		Map data = (Map) t[1];
		
		int[][] _h_pos = (int[][]) data.get(DATA_H_._H_POS);
		int[] _h_life = (int[]) data.get(DATA_H_._H_LIFE);
		int[] _h_frank = (int[]) data.get(DATA_H_._H_FRANK);
		int[] _h_me_score1 = (int[]) data.get(DATA_H_._H_ME_SCORE1);
		
		boolean[] _h_crashed = (boolean[]) data.get(DATA_H_._H_CRASHED);
		boolean[] _h_immobile = (boolean[]) data.get(DATA_H_._H_IMMOBILE);
		boolean[] _h_me_close = (boolean[]) data.get(DATA_H_._H_ME_CLOSE);
		boolean[] _h_me_approaching = (boolean[]) data.get(DATA_H_._H_ME_APPROACHING);
		boolean[] _h_me_weaker = (boolean[]) data.get(DATA_H_._H_ME_WEAKER);
		boolean[] _h_me_score1_best = (boolean[]) data.get(DATA_H_._H_ME_SCORE1_BEST);
		
		
		for(int i=0;i<_h_pos.length;i++)
		{
			int[] pos = _h_pos[i];
			int life = _h_life[i];
			int frank = _h_frank[i];
			int score = _h_me_score1[i];
			
			boolean crashed = _h_crashed[i];
			boolean immobile = _h_immobile[i];
			boolean close = _h_me_close[i];
			boolean approaching = _h_me_approaching[i];
			boolean weaker = _h_me_weaker[i];
			boolean score_best = _h_me_score1_best[i];
			
			int y = pos[0];
			int x = pos[1];
			
			
			drawLifeBar(g,life,x,y);
			
			if(crashed)
				drawCrashed(g,x,y);
			else if(DRAW_FRANK) drawFRank(g,frank,x,y);
			
			if(close && DRAW_CLOSE)
				drawIcon(g,ICONID_CLOSE,1,x,y);
			else if(approaching && DRAW_APPROACHING)
				drawIcon(g,ICONID_APPROACH,1,x,y);
			
			if(weaker && DRAW_WEAKER)
				drawIcon(g,ICONID_WEAKER,2,x,y);
			
			if(immobile && DRAW_IMMOBILE)
				drawIcon(g,ICONID_IMMOBILE,3,x,y);
			
			if(DRAW_SCORE)
				drawScore(g,score,score_best,x,y);
		}
	}
	
	
	
	private void drawIcon(Graphics2D g, String iconId, int level, int i, int j) throws Exception
	{
		Icon icon = (Icon) ip.t(iconId);
		if(icon==null) return;
		
		int x = i*GAP + LIFEBAR_THICKNESS;
		int y = j*GAP + GAP - level*icon.getIconHeight();
		icon.paintIcon(null,g,x,y);
	}

	
	
	private void drawCrashed(Graphics2D g, int i, int j) throws Exception
	{
		Icon icon = (Icon) ip.t(ICONID_CRASHED);
		if(icon==null) return;
		
		int x = i*GAP + GAP - icon.getIconWidth();
		int y = j*GAP + GAP - icon.getIconHeight();
		icon.paintIcon(null,g,x,y);
	}
	
	
	private void drawFRank(Graphics2D g, int rank, int i, int j) throws Exception
	{
		Icon icon = (Icon) ip.t(ICONID_RANK_+rank);
		if(icon==null) return;
		
		int x = i*GAP + GAP - icon.getIconWidth();
		int y = j*GAP + GAP - icon.getIconHeight();
		icon.paintIcon(null,g,x,y);
	}
	
	
	private void drawLifeBar(Graphics2D g, int life, int i, int j)
	{
		g.setColor(life>HIT?Color.BLACK:Color.RED);
		g.fillRect(i*GAP,j*GAP+GAP-life,LIFEBAR_THICKNESS,life);
	}
	
	
	private void drawScore(Graphics2D g, int score, boolean score_best, int i, int j)
	{
		g.setColor(score_best?Color.BLUE:Color.BLACK);
		g.setFont(SCORE_FONT);
		g.drawString(""+score, i*GAP, j*GAP);
	}
}
