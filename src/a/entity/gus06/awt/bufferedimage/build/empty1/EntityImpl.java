package a.entity.gus06.awt.bufferedimage.build.empty1;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200515";}


	private Service toDim;
	
	public EntityImpl() throws Exception
	{toDim = Outside.service(this,"gus06.find.dimension");}
	
	
	

	public Object t(Object obj) throws Exception
	{
		Dimension dim = (Dimension) toDim.t(obj);
		BufferedImage img = new BufferedImage(dim.width,dim.height,BufferedImage.TYPE_INT_RGB);
		return img;
	}
}
