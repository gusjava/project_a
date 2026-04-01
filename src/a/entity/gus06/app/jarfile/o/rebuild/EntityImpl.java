package a.entity.gus06.app.jarfile.o.rebuild;

import java.io.File;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140802";}


	private Service jarBuilder;
	private Service findMain;
	private Service appJar;
	

	public EntityImpl() throws Exception
	{
		jarBuilder = Outside.service(this,"gus06.file.jar.builder1");
		findMain = Outside.service(this,"gus06.app.jarfile.mainclass");
		appJar = Outside.service(this,"gus06.app.jarfile.o");
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		File out = (File) appJar.g();
		String mainClass = (String) findMain.g();
		
		jarBuilder.v("bin",obj);
		jarBuilder.v("jarFile",out);
		jarBuilder.v("mainClass",mainClass);
		
		jarBuilder.e();
	}
}
