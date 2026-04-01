package a.entity.gus06.appli.dragontale.level1.draw.player;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20200516";}

	
	private Service mvtManager;
	private Service playerImage;
	private Service playerData;


	public EntityImpl() throws Exception
	{
		mvtManager = Outside.service(this,"gus06.sys.phys2d.mvt.manager");
		playerImage = Outside.service(this,"gus06.appli.dragontale.player.image");
		playerData = Outside.service(this,"gus06.appli.dragontale.player.data");
	}



	public void p(Object obj) throws Exception
	{
		BufferedImage image = (BufferedImage) obj;
		Graphics2D g = (Graphics2D) image.getGraphics();
		
		double[] p = playerPosition();
		if(p==null) return;
		
		int x = (int) p[0];
		int y = (int) p[1];
		
		BufferedImage img = playerImage();
		if(img==null) return;
		
		int w = img.getWidth();
		int h = img.getHeight();
		
		if(facingRight()) g.drawImage(img,x,y,w,h,null);
		else g.drawImage(img,x+w,y,-w,h,null);
	}
	
	
	
	
	
	
	private BufferedImage playerImage() throws Exception
	{return (BufferedImage) playerImage.g();}
	
	
	private double[] playerPosition() throws Exception
	{return (double[]) mvtManager.r("position.player");}
	
	
	private boolean facingRight() throws Exception
	{return playerData.r(PLAYER.KEY_FACINGRIGHT).equals("true");}
}
