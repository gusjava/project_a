package a.entity.gus06.file.read.properties.safe;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250214";}
	
	public Object t(Object obj) throws Exception
	{
		File f = (File) obj;
		if(f==null || !f.isFile()) return null;
		
		Properties p = new Properties();
		load(f,p);
		return p;
	}
	
	private void load(File f, Properties p)
	{
		try
		{
			FileInputStream fis = new FileInputStream(f);
			p.load(fis);
			fis.close();
		}
		catch(Exception e)
		{Outside.err(this,"load(File,Properties)",e);}
	}
}