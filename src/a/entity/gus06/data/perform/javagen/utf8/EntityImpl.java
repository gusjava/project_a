package a.entity.gus06.data.perform.javagen.utf8;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20170223";}
	
	
	private Service performDir;
	private Service performFile;
	private Service performString;
	private Service performMap;
	
	public EntityImpl() throws Exception
	{
		performDir = Outside.service(this,"gus06.java.srcdir.generate.fromdir.utf8");
		performFile = Outside.service(this,"gus06.java.srcdir.generate.fromfile.utf8");
		performString = Outside.service(this,"gus06.java.srcdir.generate.fromsrc.utf8");
		performMap = Outside.service(this,"gus06.java.srcdir.generate.frommap.utf8");
	}


	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object input = o[0];
		File output = (File) o[1];
		
		if(input instanceof String) 
		{
			performString.p(o);
			return;
		}
		
		if(input instanceof Map) 
		{
			performMap.p(o);
			return;
		}
		
		if(isFile(input))
		{
			performFile.p(new File[]{(File)input,output});
			return;
		}
		
		if(isDir(input))
		{
			performDir.p(new File[]{(File)input,output});
			return;
		}
		
		throw new Exception("Invalid data type: "+input.getClass().getName());
	}
	
	
	private boolean isFile(Object input)
	{
		if(!(input instanceof File)) return false;
		File f = (File) input;
		return f.isFile();
	}
	
	private boolean isDir(Object input)
	{
		if(!(input instanceof File)) return false;
		File f = (File) input;
		return f.isDirectory();
	}
}
