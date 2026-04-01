package a.entity.gus06.file.runtask.properties.convert.totxtdir;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.util.Properties;
import java.util.ArrayList;
import java.io.PrintStream;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150921";}


	private Service autoRename;
	private Service read;

	public EntityImpl() throws Exception
	{
		autoRename = Outside.service(this,"gus06.file.newfile.autorename2");
		read = Outside.service(this,"gus06.file.read.properties");
	}

	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		File dir = (File) autoRename.t(file);
		dir.mkdirs();
		
		Properties prop = (Properties) read.t(file);
		ArrayList keys = new ArrayList(prop.keySet());
		int number = keys.size();
		
		if(progress!=null) ((V)progress).v("size",""+number);
		
		for(int i=0;i<number;i++)
		{
			String key = (String) keys.get(i);
			String value = prop.getProperty(key);
			
			File f = new File(dir,key+".txt");
			PrintStream p = new PrintStream(f);
			p.print(value);
			p.close();
			
			if(progress!=null) ((E)progress).e();
			if(interrupt!=null && !interrupt.isEmpty()) break;
		}
	}
}
