package a.entity.gus06.app.jarfile.replace;

import java.io.File;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140706";}

	
	
	private Service copy;
	private Service appJar;

	public EntityImpl() throws Exception
	{
		copy = Outside.service(this,"gus.x.file.op.copy");
		appJar = Outside.service(this,"gus.x.app.location.asjar");
	}
	
	
	public void p(Object obj) throws Exception
	{
		File in = (File) obj;
		File out = (File) appJar.g();
		
		copy.p(new File[]{in,out});
	}
}
