package a.entity.gus06.file.pdf.perform.convert.toprop;

import java.io.File;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20191010";}


	private Service readProp;
	private Service writeProp;
	
	public EntityImpl() throws Exception
	{
		readProp = Outside.service(this,"gus06.file.read.properties.from.pdf");
		writeProp = Outside.service(this,"gus06.file.write.properties");
	}


	public void p(Object obj) throws Exception
	{
		File[] o = (File[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File input = o[0];
		File output = o[1];
		
		Object prop = readProp.t(input);
		writeProp.p(new Object[]{output,prop});
	}
}
