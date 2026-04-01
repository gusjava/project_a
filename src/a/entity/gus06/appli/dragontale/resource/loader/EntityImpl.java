package a.entity.gus06.appli.dragontale.resource.loader;

import java.io.File;
import java.io.FileInputStream;
import javax.imageio.ImageIO;
import a.framework.*;
import java.awt.image.BufferedImage;

public class EntityImpl implements Entity, R {


	public String creationDate() {return "20200516";}


	private Service inside;
	private Service insideImage;

	private BufferedImage background0;
	private BufferedImage background1;
	private BufferedImage tileSet;
	private BufferedImage playerSprites;
	
	private String tileMap;
	
	

	public EntityImpl() throws Exception
	{
		inside = Outside.service(this,"inside");
		insideImage = Outside.service(this,"gus06.app.inside.image");
		
		background0 = (BufferedImage) insideImage.r("background/menubg");
		background1 = (BufferedImage) insideImage.r("background/grassbg1");
		playerSprites = (BufferedImage) insideImage.r("sprite/playersprites");
		tileSet = (BufferedImage) insideImage.r("tileset/grasstileset");
		
		tileMap = (String) inside.r("txt.map/level1-1.map");
	}



	
	public Object r(String key) throws Exception
	{
		if(key.equals("img-background0")) return background0;
		if(key.equals("img-background1")) return background1;
		if(key.equals("img-playerSprites")) return playerSprites;
		if(key.equals("img-tileSet")) return tileSet;
		if(key.equals("txt-tileMap")) return tileMap;
		
		if(key.equals("keys"))
			 return new String[]{
				"img-background0",
				"img-background1",
				"img-playerSprites",
				"img-tileSet",
				"txt-tileMap"};
		
		throw new Exception("Unknown key: "+key);
	}


}
