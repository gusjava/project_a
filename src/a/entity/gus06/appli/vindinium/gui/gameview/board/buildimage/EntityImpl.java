package a.entity.gus06.appli.vindinium.gui.gameview.board.buildimage;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170923";}

	public static final int GAP = 100;
	
	private Service drawTmine1;
	private Service drawStartPos;
	private Service drawMyPosition;
	private Service drawPath;
	private Service drawTiles;
	private Service drawBotFeatures;
	

	public EntityImpl() throws Exception
	{
		drawTmine1 = Outside.service(this,"gus06.appli.vindinium.gui.gameview.board.buildimage.drawtmine1");
		drawStartPos = Outside.service(this,"gus06.appli.vindinium.gui.gameview.board.buildimage.drawstartpos");
		drawMyPosition = Outside.service(this,"gus06.appli.vindinium.gui.gameview.board.buildimage.drawmyposition");
		drawPath = Outside.service(this,"gus06.appli.vindinium.gui.gameview.board.buildimage.drawpath");
		drawTiles = Outside.service(this,"gus06.appli.vindinium.gui.gameview.board.buildimage.drawtiles");
		drawBotFeatures = Outside.service(this,"gus06.appli.vindinium.gui.gameview.board.buildimage.drawbotfeatures");
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		Map data = (Map) obj;
		int[][] _board = (int[][]) data.get(DATA_._BOARD);
		
		int size = _board.length;
		
		BufferedImage img = new BufferedImage(size*GAP,size*GAP,BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D) img.getGraphics();
		
		g.setColor(Color.WHITE);
		g.fillRect(0,0,size*GAP,size*GAP);
		
		draw(g,data,drawStartPos);
		draw(g,data,drawTmine1);
		draw(g,data,drawTiles);
		draw(g,data,drawBotFeatures);
		draw(g,data,drawMyPosition);
		draw(g,data,drawPath);
				
		g.dispose();
		return img;
	}
	
	
	
	private void draw(Graphics2D g, Map data, Service s) throws Exception
	{s.p(new Object[]{g,data});}
}
