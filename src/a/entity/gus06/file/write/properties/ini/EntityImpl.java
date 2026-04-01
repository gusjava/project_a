package a.entity.gus06.file.write.properties.ini;

import a.framework.*;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.io.PrintStream;


public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160309";}


	private Service toProp;

	public EntityImpl() throws Exception
	{toProp = Outside.service(this,"gus06.find.properties");}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		Properties prop = toProp(o[1]);
		
		List keys = new ArrayList(prop.keySet());
		Collections.sort(keys);
		
		File parent = file.getParentFile();
		if(!parent.exists()) parent.mkdirs();
		
		PrintStream p = new PrintStream(file,"UTF-8");
		for(int i=0;i<keys.size();i++)
		{
			String key = (String) keys.get(i);
			if(key.contains("=") || key.contains("\n")) throw new Exception("Invalid key for ini file: "+key);
			
			String value = prop.getProperty(key);
			if(value.contains("\n")) throw new Exception("Invalid value for ini file: "+value);
			
			p.println(key+"="+value);
		}
		p.close();
	}
	
	
	private Properties toProp(Object obj) throws Exception
	{return (Properties) toProp.t(obj);}
}
