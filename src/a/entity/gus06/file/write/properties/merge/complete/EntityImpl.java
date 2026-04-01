package a.entity.gus06.file.write.properties.merge.complete;

import a.framework.*;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;
import java.io.FileInputStream;


public class EntityImpl implements Entity, P {

	public String creationDate() {return "20141015";}


	private Service toProp;

	public EntityImpl() throws Exception
	{toProp = Outside.service(this,"gus06.find.properties");}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		Properties prop = toProp(o[1]);
		
		Properties prop1 = merge(file,prop);
		write(file,prop1);
	}
	
	
	private Properties toProp(Object obj) throws Exception
	{return (Properties) toProp.t(obj);}
	
	
	private Properties merge(File file, Properties prop) throws Exception
	{
		if(file.exists())
		prop.putAll(read(file));
		return prop;
	}
	
	
	
	private Properties read(File file) throws Exception
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(file);
		prop.load(fis);
		fis.close();
		return prop;
	}
	
	private void write(File file, Properties prop)throws Exception
	{
		FileOutputStream fos = new FileOutputStream(file);
		prop.store(fos,"");
		fos.close();
	}
}
