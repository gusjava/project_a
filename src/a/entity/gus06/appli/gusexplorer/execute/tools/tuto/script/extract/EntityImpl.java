package a.entity.gus06.appli.gusexplorer.execute.tools.tuto.script.extract;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20180126";}

	public static final String START = "gus06/resource/gus/gyem/tuto/script/";
	
	
	private Service findJar;
	private Service extractor;
	private Service chooseDir;
	
	public EntityImpl() throws Exception
	{
		findJar = Outside.service(this,"gus06.app.jarfile");
		extractor = Outside.service(this,"gus06.file.jar.extractor3");
		chooseDir = Outside.service(this,"gus06.file.choose.save.dir");
	}
	
	public void e() throws Exception
	{
		File outputDir = (File) chooseDir.g();
		if(outputDir==null) return;
		
		File appJar = (File) findJar.g();
		extractor.p(new Object[]{appJar,outputDir,START});
	}
}
