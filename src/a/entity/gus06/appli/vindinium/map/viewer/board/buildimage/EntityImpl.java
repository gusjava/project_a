package a.entity.gus06.appli.vindinium.map.viewer.board.buildimage;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.Icon;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170917";}

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


	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		int[][] tiles = (int[][]) obj;
		int tileNumber = tiles.length;
		int imgSize = tileNumber*GAP;
		
		BufferedImage img = new BufferedImage(imgSize,imgSize,BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D) img.getGraphics();
		
		g.setColor(Color.WHITE);
		g.fillRect(0,0,imgSize,imgSize);
		
		for(int i=0;i<tileNumber;i++) for(int j=0;j<tileNumber;j++)
		{
			int value = tiles[j][i];
			if(value!=TILE.AIR) drawTile(g,value,i,j);
		}
		
		g.setColor(Color.BLUE);
		g.setStroke(new BasicStroke(imgSize/200));
		
		g.drawLine(0,imgSize/2,imgSize,imgSize/2);
		g.drawLine(imgSize/2,0,imgSize/2,imgSize);
		
		g.dispose();
		return img;
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
}
