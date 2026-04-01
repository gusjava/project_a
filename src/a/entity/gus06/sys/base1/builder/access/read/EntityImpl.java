package a.entity.gus06.sys.base1.builder.access.read;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150524";}


	private Service readFile;
	private Service readMap;

	public EntityImpl() throws Exception
	{
		readFile = Outside.service(this,"gus06.dir.access.read.properties");
		readMap = Outside.service(this,"gus06.sys.base1.impl.read.map");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		G g = (G) o[0];
		String id = (String) o[1];
		
		Object base = g.g();
		if(base instanceof File) return readFile.t(new Object[]{base,id});
		if(base instanceof Map) return readMap.t(new Object[]{base,id});
		
		throw new Exception("Unsupported base class: "+base.getClass().getName());
	}
}
