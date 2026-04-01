package a.entity.gus06.file.string.todir.invert;

import a.framework.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150922";}


	private Service readFile;
	private Service getName;
	private Service normalize;
	
	public EntityImpl() throws Exception
	{
		readFile = Outside.service(this,"gus06.file.read.string.array");
		getName = Outside.service(this,"gus06.file.getname0");
		normalize = Outside.service(this,"gus06.string.transform.normalize.filename");
	}
	
	
	public void p(Object obj) throws Exception
	{
		File[] o = (File[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File file = o[0];
		File dir = o[1];
		
		String name = (String) getName.t(file);
		String[] lines = (String[]) readFile.t(file);
			
		for(String line:lines)
		{
			File file1 = new File(dir,toFileName(line));
			FileOutputStream fos = new FileOutputStream(file1,true);
			PrintStream p = new PrintStream(fos);
			p.println(name);
			p.close();
		}
	}
	
	
	
	private String toFileName(String key) throws Exception
	{
		String key_ = (String) normalize.t(key);
		return key_+".txt";
	}
}
