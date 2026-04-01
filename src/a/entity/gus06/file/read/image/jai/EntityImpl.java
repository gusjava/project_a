package a.entity.gus06.file.read.image.jai;

import java.io.File;
import javax.media.jai.JAI;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150616";}


	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		return JAI.create("fileload",file.getAbsolutePath());
	}
}
