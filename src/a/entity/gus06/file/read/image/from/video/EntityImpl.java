package a.entity.gus06.file.read.image.from.video;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150616";}
	
	public static final String KEY_ENABLED = "videofile.image.enabled";
	

	private Service mosaic;
	private Service propBoolDT;

	public EntityImpl() throws Exception
	{
		mosaic = Outside.service(this,"gus06.file.video.generic.mosaic.wimg.mosaic_4_4");
		propBoolDT = Outside.service(this,"propbool_dt");
	}
	
	public Object t(Object obj) throws Exception
	{
		if(!propBoolDT.f(KEY_ENABLED)) return null;
		
		File file = (File) obj;
		return mosaic.t(file);
	}
}
