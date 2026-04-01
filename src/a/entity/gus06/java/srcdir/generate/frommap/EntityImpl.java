package a.entity.gus06.java.srcdir.generate.frommap;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20170310";}


	private Service fromSrc;
	private Service genSrc;


	public EntityImpl() throws Exception
	{
		fromSrc = Outside.service(this,"gus06.java.srcdir.generate.fromsrc");
		genSrc = Outside.service(this,"gus06.java.srccode.generate.classgenerator1");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map map = (Map) o[0];
		File dir = (File) o[1];
		
		String src = (String) genSrc.t(map);
		fromSrc.p(new Object[]{src,dir});
	}
}
