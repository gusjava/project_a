package a.entity.gus06.appli.dragontale.resource.builder.tilemap.image;

import java.awt.image.BufferedImage;
import a.framework.*;

public class EntityImpl implements Entity, G {


	public String creationDate() {return "20200516";}


	
	private Service buildTileMap;
	private Service buildTileImages;
	
	private BufferedImage[][] tileMapImage;
	private BufferedImage[] tileImages;
	private int[][] tileMap;


	

	public EntityImpl() throws Exception
	{
		buildTileMap = Outside.service(this,"gus06.appli.dragontale.resource.builder.tilemap");
		buildTileImages = Outside.service(this,"gus06.appli.dragontale.resource.builder.tileimages");
		
		tileImages = (BufferedImage[]) buildTileImages.g();
		tileMap = (int[][]) buildTileMap.g();
		
		int rowNum = tileMap.length;
		int colNum = tileMap[0].length;
		
		tileMapImage = new BufferedImage[rowNum][colNum];
		for(int i=0;i<rowNum;i++) for(int j=0;j<colNum;j++)
		tileMapImage[i][j] = findTileImage(tileMap[i][j]);
	}

	


	public Object g() throws Exception
	{return tileMapImage;}
	
	
	
	
	private BufferedImage findTileImage(int rc)
	{return rc>0?tileImages[rc]:null;}
}
