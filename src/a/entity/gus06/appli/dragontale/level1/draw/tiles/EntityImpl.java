package a.entity.gus06.appli.dragontale.level1.draw.tiles;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20200516";}


	private Service tileMapImage;

	public EntityImpl() throws Exception
	{
		tileMapImage = Outside.service(this,"gus06.appli.dragontale.resource.builder.tilemap.image");
	}



	public void p(Object obj) throws Exception
	{
		BufferedImage image = (BufferedImage) obj;
		Graphics2D g = (Graphics2D) image.getGraphics();
		
		BufferedImage[][] tile = (BufferedImage[][]) tileMapImage.g();
		int w = tile[0][0].getWidth();
		int h = tile[0][0].getHeight();
		
		int rowNum = tile.length;
		int colNum = tile[0].length;
		
		for(int i=0;i<rowNum;i++) for(int j=0;j<colNum;j++)
		{
			BufferedImage t = tile[i][j];
			if(t!=null && isInside(image,j,w))
				g.drawImage(t,j*w,i*h,w,h,null);
		}
	}
	
	
	
	
	private boolean isInside(BufferedImage image, int col, int width)
	{
		return col * width < image.getWidth();
	}
}
