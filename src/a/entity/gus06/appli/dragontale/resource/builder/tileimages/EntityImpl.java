package a.entity.gus06.appli.dragontale.resource.builder.tileimages;

import java.awt.image.BufferedImage;
import a.framework.*;

public class EntityImpl implements Entity, G {


	public String creationDate() {return "20200516";}


	
	private Service loader; 
	private BufferedImage[] tiles;
	


	
	public EntityImpl() throws Exception
	{
		loader = Outside.service(this,"gus06.appli.dragontale.resource.loader");
		
		
		BufferedImage image = (BufferedImage) loader.r("img-tileSet");
		
		int width = image.getWidth();
		int height = image.getHeight();
		
		int row = 2;
		int size = (int) ((double)height/(double)row);
		int col = (int) ((double)width/(double)size);
		int number = col*row;
		
		tiles = new BufferedImage[number];
		for(int i=0; i<row; i++) for(int j=0; j<col; j++) 
		tiles[i*col+j] = image.getSubimage(j*size,i*size, size, size);
	}

	


	public Object g() throws Exception
	{return tiles;}


}
