package a.entity.gus06.sys.base1.builder.access.write;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150524";}


	private Service writeFile;
	private Service writeMap;
	
	public EntityImpl() throws Exception
	{
		writeFile = Outside.service(this,"gus06.dir.access.write.properties");
		writeMap = Outside.service(this,"gus06.sys.base1.impl.write.map");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		G g = (G) o[0];
		String id = (String) o[1];
		Object data = o[2];
		
		Object base = g.g();
		if(base instanceof File) {writeFile.p(new Object[]{base,id,data});return;}
		if(base instanceof Map) {writeMap.p(new Object[]{base,id,data});return;}
		
		throw new Exception("Unsupported base class: "+base.getClass().getName());
	}
}
