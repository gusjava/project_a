package a.entity.gus06.file.zip.perform.quickzip;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20180131";}


	private Service runZip;
	private Service getName;
	
	public EntityImpl() throws Exception
	{
		runZip = Outside.service(this,"gus06.file.zip.run.zip");
		getName = Outside.service(this,"gus.x.file.getname0");
	}


	
	public void p(Object obj) throws Exception
	{
		File input = (File) obj;
		
		File parent = input.getParentFile();
		String name = (String) getName.t(input);
		File zipFile = new File(parent,name+".zip");
		
		runZip.p(new Object[]{input,zipFile,null,null});
	}
}