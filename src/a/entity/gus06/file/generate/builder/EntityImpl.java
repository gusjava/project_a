package a.entity.gus06.file.generate.builder;

import a.framework.*;
import java.io.FileOutputStream;
import java.io.File;

public class EntityImpl implements Entity, V {

	public String creationDate() {return "20151002";}

	
	
	public void v(String key, Object obj) throws Exception
	{
		long size = Long.parseLong(key);
		File file = (File) obj;
		
		buildFile(file,size);
	}
	
	
	
	private void buildFile(File file, long size) throws Exception
	{
		FileOutputStream fos = new FileOutputStream(file);
    		for(long i=0;i<size;i++) fos.write((byte) 0);
    		fos.close();
	}
}
