package a.entity.gus06.dir.access.write.properties.indexed;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20161008";}
	


	private Service write;
	private Service getFile;

	public EntityImpl() throws Exception
	{
		write = Outside.service(this,"gus06.file.write.properties");
		getFile = Outside.service(this,"gus06.dir.access.getfile.properties.indexed");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object data = o[1];
		
		File f = (File) getFile.t(dir);
		write.p(new Object[]{f,data});
	}
}
