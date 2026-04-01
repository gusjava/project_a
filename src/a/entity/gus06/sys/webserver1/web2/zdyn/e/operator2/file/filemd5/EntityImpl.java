package a.entity.gus06.sys.webserver1.web2.zdyn.e.operator2.file.filemd5;

import a.framework.*;
import java.util.Map;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141106";}


	private Service findVar;
	private Service buildMd5;
	
	public EntityImpl() throws Exception
	{
		findVar = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.var.build");
		buildMd5 = Outside.service(this,"gus06.crypto.hash.md5.hexa");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=4) throw new Exception("Wrong data number: "+o.length);
		
		R mr = (R) o[0];
		String info = (String) o[1];
		Map args = (Map) o[2];
		Map vars = (Map) o[3];
		
		File file = (File) findVar.t(new Object[]{vars,info});
		if(file==null) throw new Exception("Invalid null variable: "+info);
		
		return buildMd5.t(file);
	}
}
