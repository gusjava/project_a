package a.entity.gus06.appdev.gyem.updatebuild;

import a.framework.*;
import java.io.PrintStream;
import java.io.File;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20140926";}


	private Service findToday;
	private Service extract;
	private Service readFile;
	
	private File file;
	


	public EntityImpl() throws Exception
	{
		findToday = Outside.service(this,"gus06.time.today");
		readFile = Outside.service(this,"gus.x.file.string.read.v1");
		extract = Outside.service(this,"gus06.java.srccode.extract.gyem.build");
		
		file = (File) Outside.resource(this,"dev_gyem_version");
	}
	
	
	public void e() throws Exception
	{
		String text = (String) readFile.t(file);
		String build = (String) extract.t(text);
		
		String today = (String) findToday.g();
		if(build.equals(today)) return;
		
		PrintStream p = new PrintStream(file);
		p.print(text.replace(build,today));
		p.close();
	}
}
