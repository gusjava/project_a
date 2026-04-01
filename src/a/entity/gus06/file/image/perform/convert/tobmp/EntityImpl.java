package a.entity.gus06.file.image.perform.convert.tobmp;

import java.io.File;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20151023";}


	private Service read;
	private Service write;
	
	public EntityImpl() throws Exception
	{
		read = Outside.service(this,"gus06.file.read.image.generic");
		write = Outside.service(this,"gus06.file.write.image.jai.bmp");
	}


	public void p(Object obj) throws Exception
	{
		File[] o = (File[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File input = o[0];
		File output = o[1];
		
		Object data = read.t(input);
		write.p(new Object[]{output,data});
	}
}
