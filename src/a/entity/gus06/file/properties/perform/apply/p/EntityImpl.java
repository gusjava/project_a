package a.entity.gus06.file.properties.perform.apply.p;

import java.io.File;
import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150925";}


	private Service readFile;
	private Service writeFile;
	
	public EntityImpl() throws Exception
	{
		readFile = Outside.service(this,"gus.x.file.prop.read");
		writeFile = Outside.service(this,"gus06.file.write.properties");
	}


	

	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		P p = (P) o[1];
		
		Map prop = (Map) readFile.t(file);
		p.p(prop);
		writeFile.p(new Object[]{file,prop});
	}
}
