package a.entity.gus06.convert.iconstoicon;

import a.framework.*;
import java.awt.Image;
import javax.swing.ImageIcon;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140913";}


	private Service iconsToImage;


	public EntityImpl() throws Exception
	{
		iconsToImage = Outside.service(this,"gus06.convert.iconstoimage");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Image image = (Image) iconsToImage.t(obj);
		return new ImageIcon(image);
	}
}
