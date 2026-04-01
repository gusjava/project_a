package a.entity.gus06.icon.transform.color.hue;

import a.framework.*;
import java.awt.Image;
import javax.swing.ImageIcon;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20230228";}


	private Service imageTransform;

	public EntityImpl() throws Exception
	{
		imageTransform = Outside.service(this,"gus06.awt.bufferedimage.transform.color.hue");
	}
	
	public Object t(Object obj) throws Exception
	{
		Image image = (Image) imageTransform.t(obj);
		return new ImageIcon(image);
	}
}
