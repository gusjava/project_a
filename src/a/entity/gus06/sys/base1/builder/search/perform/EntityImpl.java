package a.entity.gus06.sys.base1.builder.search.perform;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150529";}


	private Service performDir;


	public EntityImpl() throws Exception
	{
		performDir = Outside.service(this,"gus06.sys.base1.builder.search.perform.dir");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=4) throw new Exception("Wrong data number: "+o.length);
		
		G g = (G) o[0];
		
		Object base = g.g();
		Object[] o1 = new Object[]{base,o[1],o[2],o[3]};
		
		if(base instanceof File) return performDir.t(o1);
		
		throw new Exception("Unsupported base type: "+base.getClass().getName());
	}
}
