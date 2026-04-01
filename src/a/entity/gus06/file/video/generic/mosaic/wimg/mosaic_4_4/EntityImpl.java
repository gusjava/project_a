package a.entity.gus06.file.video.generic.mosaic.wimg.mosaic_4_4;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200106";}
	
	public static final int NB = 4;


	private Service mosaic;

	public EntityImpl() throws Exception
	{
		mosaic = Outside.service(this,"gus06.file.video.generic.mosaic");
	}
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		return mosaic.t(new Object[]{file,null,NB});
	}
}