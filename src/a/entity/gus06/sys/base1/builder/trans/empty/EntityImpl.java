package a.entity.gus06.sys.base1.builder.trans.empty;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150526";}


	private Service emptyDir;
	
	public EntityImpl() throws Exception
	{
		emptyDir = Outside.service(this,"gus06.dir.perform.empty");
	}
	
	
	public void p(Object obj) throws Exception
	{
		G g = (G) obj;
		Object base = g.g();
		
		if(base instanceof File) {emptyDir.p(base);return;}
		
		throw new Exception("Unsupported base type: "+base.getClass().getName());
	}
}
