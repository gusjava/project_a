package a.entity.gus06.file.video.checksize.movie;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20191130";}

	public static final long LIMIT_500MB = 500000000L;
	
	
	public boolean f(Object obj) throws Exception
	{
		File file = (File) obj;
		return file.isFile() && file.length()>=LIMIT_500MB;
	}
}
