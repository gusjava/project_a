package a.entity.gus06.file.properties.perform.renamefile.fromfield;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150924";}


	private Service autoRename;
	private Service normalize;
	
	public static final String DEFAULT_VALUE = "NOTFOUND";


	public EntityImpl() throws Exception
	{
		autoRename = Outside.service(this,"gus06.file.op.move.autorename");
		normalize = Outside.service(this,"gus06.string.transform.normalize.filename");
	}

	

	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		String field = (String) o[1];
		
		Properties prop = prop(file);
		String newName = prop.containsKey(field)?prop.getProperty(field):DEFAULT_VALUE;
		newName = (String) normalize.t(newName);
		
		File newFile = new File(file.getParentFile(),newName+".properties");
		autoRename.p(new File[]{file,newFile});
	}

	
	
	private Properties prop(File file) throws Exception
	{
		Properties p = new Properties();
		FileInputStream fis = new FileInputStream(file);
		p.load(fis);
		fis.close();
		return p;
	}
}
