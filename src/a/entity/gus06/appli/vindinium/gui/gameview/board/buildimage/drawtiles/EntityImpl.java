package a.entity.gus06.appli.vindinium.gui.gameview.board.buildimage.drawtiles;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Map;
import javax.swing.Icon;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20170923";}

	public static final int GAP = 100;
	
	
	private Service heroColors;
	private Service ip;
	
	private Color[] colors;

	public EntityImpl() throws Exception
	{
		heroColors = Outside.service(this,"gus06.appli.vindinium.data.hero.colorarray");
		ip = Outside.service(this,"gus06.icon.provider");
		
		colors = (Color[]) heroColors.g();
	}


	public void p(Object obj) throws Exception
	{
		Object[] t = (Object[]) obj;
		if(t.length!=2) throw new Exception("Wrong data number: "+t.length);
		
		Graphics2D g = (Graphics2D) t[0];
		Map data = (Map) t[1];
		
		int[][] _board = (int[][]) data.get(DATA_._BOARD);
		int size = _board.length;
		
		
		for(int i=0;i<size;i++) for(int j=0;j<size;j++)
		{
			int value = _board[j][i];
			if(value!=TILE.AIR) drawTile(g,value,i,j);
		}
	}
	

	
	private void drawTile(Graphics2D g, int value, int i, int j) throws Exception
	{
		Icon icon = findTileIcon(value);
		if(icon!=null) {icon.paintIcon(null,g,i*GAP,j*GAP);return;}
		
		Color color = findTileColor(value);
		if(isRect(value)) fillRect(g,color,i,j);
		else fillOval(g,color,i,j);
	}
	
	
	private Icon findTileIcon(int value) throws Exception
	{return (Icon) ip.t("GAME_vindinium_tile"+value);}
	
	
	private void fillRect(Graphics2D g, Color color, int i, int j)
	{
		g.setColor(color);
		g.fillRect(i*GAP,j*GAP,GAP,GAP);
	}
	
	
	private void fillOval(Graphics2D g, Color color, int i, int j)
	{
		g.setColor(color);
		g.fillOval(i*GAP,j*GAP,GAP,GAP);
	}
	
	
	
	private boolean isRect(int value)
	{
		if(value==TILE.HERO1) return false;
		if(value==TILE.HERO2) return false;
		if(value==TILE.HERO3) return false;
		if(value==TILE.HERO4) return false;
		
		if(value==TILE.MINE1) return true;
		if(value==TILE.MINE2) return true;
		if(value==TILE.MINE3) return true;
		if(value==TILE.MINE4) return true;
		
		if(value==TILE.MINE) return true;
		if(value==TILE.WALL) return true;
		if(value==TILE.TAVERN) return true;
		
		return true;
	}
	
	
	private Color findTileColor(int value)
	{
		switch(value){

		case TILE.HERO1: return colors[0];
		case TILE.HERO2: return colors[1];
		case TILE.HERO3: return colors[2];
		case TILE.HERO4: return colors[3];

		case TILE.MINE1: return colors[0];
		case TILE.MINE2: return colors[1];
		case TILE.MINE3: return colors[2];
		case TILE.MINE4: return colors[3];

		case TILE.AIR: return Color.WHITE;
		case TILE.MINE: return Color.GRAY;
		case TILE.WALL: return Color.BLACK;
		case TILE.TAVERN: return Color.ORANGE.darker();

		default:return Color.BLACK;
		}
	}
}
