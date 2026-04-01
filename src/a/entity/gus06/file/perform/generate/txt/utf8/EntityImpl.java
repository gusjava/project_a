package a.entity.gus06.file.perform.generate.txt.utf8;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20210609";}


	private Service fileToText;
	private Service writeText;


	public EntityImpl() throws Exception
	{
		fileToText = Outside.service(this,"gus06.file.read.string.generic");
		writeText = Outside.service(this,"gus06.file.write.string.cs.utf8");
	}
	
	
	public void p(Object obj) throws Exception
	{
		File[] o = (File[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String s = (String) fileToText.t(o[0]);
		writeText.p(new Object[]{o[1],s});
	}
}