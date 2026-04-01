package a.entity.gus06.appli.vindinium.gui.gameview.playerstate;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import java.awt.Color;
import java.awt.GridLayout;
import java.text.NumberFormat;
import java.util.List;
import java.util.Map;

public class EntityImpl implements Entity, P, I {

	public String creationDate() {return "20170923";}

	public static final Color COLOR = new Color(153,204,255);
	public static final Color COLOR1 = new Color(0,153,255);
	
	public static final String ICONID = "GAME_vindinium24_tile";
	public static NumberFormat nf = NumberFormat.getInstance();
	

	
	private Service form1;
	private Service form2;
	private Service repaint;
	
	private JProgressBar bar_life;
	private JProgressBar bar_mines;
	private JProgressBar bar_gold;
	
	private JLabel label_name;
	private JLabel label_strategy;
	private JLabel label_target;
	private JLabel label_computingTime;
	
	private JPanel panel;




	public EntityImpl() throws Exception
	{
		form1 = Outside.service(this,"*gus06.swing.panel.formpanel-1");
		form2 = Outside.service(this,"*gus06.swing.panel.formpanel-2");
		repaint = Outside.service(this,"gus06.swing.label.cust2.display");
		
		bar_life = bar();
		bar_mines = bar();
		bar_gold = bar();
		
		label_name = new JLabel(" ");
		label_strategy = new JLabel(" ");
		label_target = new JLabel(" ");
		label_computingTime = new JLabel(" ");
		
		bar_life.setMaximum(100);
		
		form1.v("Name",label_name);
		form1.v("Target",label_target);
		form1.v("Strategy",label_strategy);
		form1.v("Max time",label_computingTime);

		form2.v("Gold",bar_gold);
		form2.v("Mines",bar_mines);
		form2.v("Life",bar_life);
		
		
		panel = new JPanel(new GridLayout(1,2));
		panel.add((JComponent) form1.i());
		panel.add((JComponent) form2.i());
	}


	public Object i() throws Exception
	{return panel;}
	
	
	
	
	
	private JProgressBar bar()
	{
		JProgressBar bar = new JProgressBar();
		bar.setForeground(COLOR);
		bar.setBorderPainted(false);
		bar.setStringPainted(true);
		return bar;
	}
	
	
	



	public void p(Object obj) throws Exception
	{
		Map data = (Map) obj;
		
		int life = ((int[]) data.get(DATA_ME_._ME_STATE))[1];
		
		int gold = ((int[]) data.get(DATA_ME_._ME_STATE))[2];
		int goldMax = findGoldMax(data);
		
		int mines = ((List) data.get(DATA_._MINE_ME)).size();
		int minesTotal = ((List) data.get(DATA_._MINE)).size();
		
		
		
		bar_life.setValue(life);
		bar_life.setString(life+" / 100");
		
		bar_gold.setMaximum(goldMax);
		bar_gold.setValue(gold);
		bar_gold.setString(gold+" / "+goldMax);

		bar_mines.setMaximum(minesTotal);
		bar_mines.setValue(mines);
		bar_mines.setString(mines+" / "+minesTotal);
		
		updateForeground(bar_life);
		updateForeground(bar_gold);
		updateForeground(bar_mines);
		
		
		
		String targetDisplay = findTargetDisplay(data);
		repaint.v(targetDisplay,label_target);
		
		String strategy = (String) data.get(DATA_BOT_._BOT_STRATEGY);
		if(isValid(strategy)) label_strategy.setText(strategy);
		
		String name = (String) data.get(DATA_BOT_._BOT_NAME);
		if(isValid(name)) label_name.setText(name);
		
		String computingTime = (String) data.get(DATA_BOT_._BOT_MAXDURATION);
		if(isValid(computingTime)) label_computingTime.setText(nf.format(Long.parseLong(computingTime))+" ns");
	}
	
	
	
	
	
	private int findGoldMax(Map data)
	{
		int max = 0;
		int[] golds = (int[]) data.get(DATA_H_._H_GOLD);
		for(int i=0;i<golds.length;i++) if(golds[i]>max) max = golds[i];
		return max;
	}
	
	
	
	
	private void updateForeground(JProgressBar bar)
	{
		if(bar.getValue()==bar.getMaximum())
			bar.setForeground(COLOR1);
		else bar.setForeground(COLOR);
	}
	
	
	
	
	
	
	
	
	private String findTargetDisplay(Map data)
	{
		int[][] path = (int[][]) data.get(DATA_BOT_._BOT_PATH);
		if(path==null || path.length<2) return "No target";
		
		int[][] board = (int[][]) data.get(DATA_._BOARD);
		
		int[] point = path[path.length-1];
		String point_ = "["+point[0]+" "+point[1]+"]";
		
		int tile = board[point[0]][point[1]];
		int dist = path.length;
		
		return ICONID+tile+"#"+point_+" (distance="+dist+")";
	}
	
	
	
	private boolean isValid(String s)
	{return s!=null && !s.equals("");}
}