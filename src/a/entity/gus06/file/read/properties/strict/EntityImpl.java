package a.entity.gus06.file.read.properties.strict;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180119";}
	
	public Object t(Object obj) throws Exception
	{
		File f = (File) obj;
		if(f==null) throw new Exception("File object is null");
		if(!f.isFile()) throw new Exception("File not found: "+f);
		
		Properties p = new Properties();
		FileInputStream fis = new FileInputStream(f);
		p.load(fis);
		fis.close();
		return p;
	}
}