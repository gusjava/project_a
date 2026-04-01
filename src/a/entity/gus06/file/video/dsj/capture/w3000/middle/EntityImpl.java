package a.entity.gus06.file.video.dsj.capture.w3000.middle;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191231";}

	public static final double POS = 0.5;

	private Service capture;

	public EntityImpl() throws Exception
	{
		capture = Outside.service(this,"gus06.file.video.dsj.capture.w3000");
	}

	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		return capture.t(new Object[]{file,POS});
	}
}
