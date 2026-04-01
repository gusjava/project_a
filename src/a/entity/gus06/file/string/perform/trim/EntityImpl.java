package a.entity.gus06.file.string.perform.trim;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150817";}


	private Service read;
	private Service write;


	public EntityImpl() throws Exception
	{
		read = Outside.service(this,"gus06.file.read.string.autodetect");
		write = Outside.service(this,"gus06.file.write.string.autodetect");
	}
	
	
	public void p(Object obj) throws Exception
	{
		File f = (File) obj;
		String s = (String) read.t(f);
		if(s==null) return;
		
		write.p(new Object[]{f,s.trim()});
	}
}
