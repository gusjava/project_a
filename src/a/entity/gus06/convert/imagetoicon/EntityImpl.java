package a.entity.gus06.convert.imagetoicon;

import a.framework.*;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Image;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250228";}

	public Object t(Object obj) throws Exception
	{
		Image image = (Image) obj;
		return new ImageIcon(image);
	}
}
