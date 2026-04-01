package a.entity.gus06.file.video.dsj.capture.w3000;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191231";}

	public static final int WIDTH = 3000;

	private Service capture;

	public EntityImpl() throws Exception
	{
		capture = Outside.service(this,"gus06.file.video.dsj.capture");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		Object posObj = o[1];
		
		return capture.t(new Object[]{file,posObj,WIDTH});
	}
}
