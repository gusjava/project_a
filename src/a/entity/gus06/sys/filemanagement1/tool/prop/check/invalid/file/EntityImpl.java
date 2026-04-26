package a.entity.gus06.sys.filemanagement1.tool.prop.check.invalid.file;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20201010";}


	private Service readFile;
	
	public EntityImpl() throws Exception
	{
		readFile = Outside.service(this,"gus.x.file.prop.read");
	}
	
	
	public void p(Object obj) throws Exception
	{
		File file = (File) obj;
		Map prop = (Map) readFile.t(file);
		
		if(!prop.containsKey("md5")) throw new Exception("md5 key not found inside prop");
		if(!prop.containsKey("ext")) throw new Exception("ext key not found inside prop");
		if(!prop.containsKey("mime")) throw new Exception("mime key not found inside prop");
		if(!prop.containsKey("size")) throw new Exception("size key not found inside prop");
		if(!prop.containsKey("name0")) throw new Exception("name0 key not found inside prop");
		if(!prop.containsKey("time")) throw new Exception("time key not found inside prop");
		
		String md5 = (String) prop.get("md5");
		if(!file.getName().equals(md5+".properties"))
		{
			throw new Exception("corrupted md5 found inside prop: "+md5);
		}
		
		String size = (String) prop.get("size");
		
		try
		{
			long v = Long.parseLong(size);
			if(v<=0) throw new Exception("corrupted size found inside prop: "+size);
		}
		catch(NumberFormatException e)
		{throw new Exception("corrupted size found inside prop: "+size);}
	}
}
