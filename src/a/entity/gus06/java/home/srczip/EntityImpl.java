package a.entity.gus06.java.home.srczip;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20140819";}

	private Service findJdk;
	private File file;
	
	public EntityImpl() throws Exception
	{
		findJdk = Outside.service(this,"gus06.java.jdk.currentdir");
	}
	
	
	public Object g() throws Exception
	{
		if(file==null) init();
		return file;
	}
	
	
	private void init() throws Exception
	{
		File dir = (File) findJdk.g();
		if(dir==null) return;
		
		file = new File(dir,"src.zip");
		if(!file.exists()) throw new Exception("File not found: "+file);
	}
}
