package a.entity.gus06.file.read.image.imageio.lazy;

import a.framework.*;
import java.io.File;
import javax.imageio.ImageIO;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201119";}
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		return new Holder(file);
	}
	
	
	private class Holder implements G
	{
		private File file;
		public Holder(File file){this.file = file;}
		
		public Object g() throws Exception
		{return ImageIO.read(file);}
	}
}