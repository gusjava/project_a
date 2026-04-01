package a.entity.gus06.appli.dragontale.resource.builder.playersprites;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20200516";}

	
	public static final int[] NUMFRAMES = {2, 8, 1, 2, 4, 2, 5};
	public static final int SPRITE_H = 30;
	public static final int SPRITE_W = 30;
	public static final int NUMBER = 7;

	
	private Service loader; 
	private List sprites;
	


	
	public EntityImpl() throws Exception
	{
		loader = Outside.service(this,"gus06.appli.dragontale.resource.loader");
		
		sprites = new ArrayList(NUMBER);
		
		BufferedImage image = (BufferedImage) loader.r("img-playerSprites");
		for(int i=0;i<NUMBER;i++)
		{
			int num = NUMFRAMES[i];
			BufferedImage[] bi = new BufferedImage[num];
			for(int j=0;j<num;j++)
			{
				int w = (i==NUMBER-1)?SPRITE_W*2:SPRITE_W;
				int h = SPRITE_H;
				bi[j] = image.getSubimage(j*w,i*h,w,h);
			}
			sprites.add(bi);
		}
	}

	


	public Object g() throws Exception
	{return sprites;}


}
