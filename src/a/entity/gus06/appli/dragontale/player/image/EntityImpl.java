package a.entity.gus06.appli.dragontale.player.image;

import java.awt.image.BufferedImage;
import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20200516";}



	private Service playerSprites;

	public EntityImpl() throws Exception
	{
		playerSprites = Outside.service(this,"gus06.appli.dragontale.resource.builder.playersprites");
	}



	public Object g() throws Exception
	{
		return get(0,0);
	}
	
	
	
	
	
	private BufferedImage get(int a, int b) throws Exception
	{
		List sprites = (List) playerSprites.g();
		BufferedImage[] sprite = (BufferedImage[]) sprites.get(a);
		return sprite[b];
	}


}
