package a.entity.gus06.file.string.perform.apply.t;

import java.io.File;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150928";}


	private Service readFile;
	private Service writeFile;
	
	public EntityImpl() throws Exception
	{
		readFile = Outside.service(this,"gus06.file.read.string.autodetect");
		writeFile = Outside.service(this,"gus06.file.write.string.autodetect");
	}


	

	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		T t = (T) o[1];
		
		String text = (String) readFile.t(file);
		String text1 = (String) t.t(text);
		
		if(!text1.equals(text))
		writeFile.p(new Object[]{file,text1});
	}
}
