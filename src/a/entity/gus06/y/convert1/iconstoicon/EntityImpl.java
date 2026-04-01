package a.entity.gus06.y.convert1.iconstoicon;

import java.awt.Image;
import javax.swing.ImageIcon;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20251113";}

	private Service iconsToImage;

	public EntityImpl() throws Exception
	{
		iconsToImage = Outside.service(this, "gus.y.convert1.iconstoimage");
	}

	public Object t(Object obj) throws Exception
	{
		Image image = (Image) iconsToImage.t(obj);
		return new ImageIcon(image);
	}
}