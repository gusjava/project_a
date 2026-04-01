package a.entity.gus06.appli.vindinium.gui.gameview.session.boardsummary;

import a.framework.*;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;
import java.util.Map;

public class EntityImpl implements Entity, P, I {

	public String creationDate() {return "20170923";}
	
	
	public static final String BLANK = "	";
	public static final Font FONT = new Font("Courier",Font.PLAIN,12);
	

	private Icon icon_air;
	private Icon icon_wall;
	private Icon icon_beer;
	private Icon icon_mine;
	
	private JPanel panel;
	
	private JLabel label_size;
	private JLabel label_air;
	private JLabel label_wall;
	private JLabel label_beer;
	private JLabel label_mine;



	public EntityImpl() throws Exception
	{
		icon_air = (Icon) Outside.resource(this,"icon#GAME_vindinium24_tile0");
		icon_wall = (Icon) Outside.resource(this,"icon#GAME_vindinium24_tile6");
		icon_beer = (Icon) Outside.resource(this,"icon#GAME_vindinium24_tile5");
		icon_mine = (Icon) Outside.resource(this,"icon#GAME_vindinium24_tile10");
		
		label_size = new JLabel(" ");
		label_size.setFont(FONT);
		
		label_air = label(icon_air);
		label_wall = label(icon_wall);
		label_beer = label(icon_beer);
		label_mine = label(icon_mine);
		
		JPanel p_tile = new JPanel(new GridLayout(1,4));
		p_tile.add(label_air);
		p_tile.add(label_wall);
		p_tile.add(label_beer);
		p_tile.add(label_mine);
		
		panel = new JPanel(new BorderLayout());
		panel.add(label_size,BorderLayout.CENTER);
		panel.add(p_tile,BorderLayout.EAST);
	}
	
	
	private JLabel label(Icon icon)
	{
		JLabel l = new JLabel(BLANK);
		l.setIcon(icon);
		l.setFont(FONT);
		return l;
	}
	

	public void p(Object obj) throws Exception
	{
		Map data = (Map) obj;
		
		int size = ((int[][])  data.get(DATA_._BOARD)).length;
		int air = ((List) data.get(DATA_._AIR)).size();
		int wall = ((List) data.get(DATA_._WALL)).size();
		int beer = ((List) data.get(DATA_._BEER)).size();
		int mine = ((List) data.get(DATA_._MINE)).size();
		
		Map game = (Map) data.get(DATA.K_GAME);
		String gameId = (String) game.get(DATA.G_ID);
		
		
		label_size.setText(gameId+" ["+size+"]");
		label_air.setText(formatString(air));
		label_wall.setText(formatString(wall));
		label_beer.setText(formatString(beer));
		label_mine.setText(formatString(mine));
	}



	public Object i() throws Exception
	{return panel;}


	
	private String formatString(int value)
	{
		int length = BLANK.length();
		String s = ""+value;
		while(s.length()<length){s = s+" ";}
		return s;
	}
}
