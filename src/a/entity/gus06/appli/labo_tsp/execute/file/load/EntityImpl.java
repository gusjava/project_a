package a.entity.gus06.appli.labo_tsp.execute.file.load;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20190305";}


	private Service openFile;
	private Service readFile;
	private Service importData;


	public EntityImpl() throws Exception
	{
		openFile = Outside.service(this,"gus06.file.choose.open.file");
		readFile = Outside.service(this,"gus.x.file.string.read.v1");
		importData = Outside.service(this,"gus06.appli.labo_tsp.data.import1");
	}
	
	
	public void e() throws Exception
	{
		File file = (File) openFile.g();
		if(file==null || !file.isFile()) return;
		
		String s = (String) readFile.t(file);
		importData.p(s);
	}
}
