package a.entity.gus06.appli.labo_tsp.execute.file.save;

import a.framework.*;
import java.io.File;
import java.io.PrintStream;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20190305";}


	private Service saveFile;
	private Service exportData;

	public EntityImpl() throws Exception
	{
		saveFile = Outside.service(this,"gus06.file.choose.save.file.ext.txt.en");
		exportData = Outside.service(this,"gus06.appli.labo_tsp.data.export1");
	}
	
	
	public void e() throws Exception
	{
		File file = (File) saveFile.g();
		if(file==null || file.isDirectory()) return;
		
		String s = (String) exportData.g();
		
		PrintStream p = new PrintStream(file);
		p.print(s);
		p.close();
	}
}
