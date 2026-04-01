package a.entity.gus06.sys.base1.builder.access.check;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20150524";}


	private Service checkFile;
	private Service checkMap;
	
	public EntityImpl() throws Exception
	{
		checkFile = Outside.service(this,"gus06.dir.access.check.properties");
		checkMap = Outside.service(this,"gus06.sys.base1.impl.check.map");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		G g = (G) o[0];
		String id = (String) o[1];
		
		Object base = g.g();
		if(base instanceof File) return checkFile.f(new Object[]{base,id});
		if(base instanceof Map) return checkMap.f(new Object[]{base,id});
		
		throw new Exception("Unsupported base class: "+base.getClass().getName());
	}
}
