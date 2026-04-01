package a.entity.gus06.file.properties.perform.classify.forfield;

import a.framework.*;
import java.util.Properties;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150619";}


	private Service readFile;
	private Service normalize;
	private Service moveFile;
	
	public EntityImpl() throws Exception
	{
		readFile = Outside.service(this,"gus06.file.read.properties");
		normalize = Outside.service(this,"gus06.string.transform.normalize.filename");
		moveFile = Outside.service(this,"gus06.dir.perform.movefile");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		File dir = (File) o[1];
		String field = (String) o[2];
		
		Properties p = (Properties) readFile.t(file);
		if(!p.containsKey(field)) return;
		
		String value = p.getProperty(field);
		String value_ = (String) normalize.t(value);
		
		File dir1 = new File(dir,value_);
		dir1.mkdirs();
		
		moveFile.p(new File[]{file,dir1});
	}
}
