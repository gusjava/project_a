package a.entity.gus06.file.read.ico.asicon;

import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191217";}

	private Service read;

	public EntityImpl() throws Exception
	{
		read = Outside.service(this,"gus06.file.read.ico.asimage");
	}

	public Object t(Object obj) throws Exception
	{
		BufferedImage image = (BufferedImage) read.t(obj);
		if(image==null) return null;
		return new ImageIcon(image);
	}
}
