package a.entity.gus06.sys.expression1.apply.op._read_prop_init;

import a.framework.*;
import java.io.File;
import java.util.Properties;
import java.io.InputStream;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20221009";}


	private Service readProperties;
	private Service handleString;
	private Service handleInputStream;
	
	public EntityImpl() throws Exception
	{
		readProperties = Outside.service(this,"gus06.file.read.properties.autodetect.init");
		handleString = Outside.service(this,"gus06.convert.stringtoproperties");
		handleInputStream = Outside.service(this,"gus06.convert.inputstreamtoproperties");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof File)
			return readProperties.t(obj);
		if(obj instanceof InputStream)
			return handleInputStream.t(obj);
		if(obj instanceof String)
			return handleString((String) obj);
			
		if(obj instanceof File[])
			return readProperties((File[]) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private Object handleString(String s) throws Exception
	{
		File f = new File(s);
		if(f.isFile()) return readProperties.t(f);
		return handleString.t(s);
	}
	
	private Properties[] readProperties(File[] ff) throws Exception
	{
		Properties[] pp = new Properties[ff.length];
		for(int i=0;i<ff.length;i++)
		pp[i] = (Properties) readProperties.t(ff[i]);
		return pp;
	}
}