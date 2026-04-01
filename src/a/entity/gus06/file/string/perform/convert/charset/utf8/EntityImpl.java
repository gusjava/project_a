package a.entity.gus06.file.string.perform.convert.charset.utf8;

import java.io.File;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160310";}


	private Service read;
	private Service write;
	
	public EntityImpl() throws Exception
	{
		read = Outside.service(this,"gus06.file.read.string.autodetect");
		write = Outside.service(this,"gus06.file.write.string.cs.utf8");
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
