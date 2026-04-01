package a.entity.gus06.dirfile.perform.display.infos1;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20151004";}


	private Service handleFile;
	private Service handleDir;


	public EntityImpl() throws Exception
	{
		handleFile = Outside.service(this,"gus06.file.perform.display.infos1");
		handleDir = Outside.service(this,"gus06.dir.perform.display.infos1");
	}
	
	
	public void p(Object obj) throws Exception
	{
		File f = (File) obj;
		if(f.isFile()) handleFile.p(f);
		else handleDir.p(f);
	}
}
