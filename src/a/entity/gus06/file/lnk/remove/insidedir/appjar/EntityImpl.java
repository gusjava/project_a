package a.entity.gus06.file.lnk.remove.insidedir.appjar;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, F, P {

	public String creationDate() {return "20180309";}


	private Service remove;
	private Service findJar;

	public EntityImpl() throws Exception
	{
		remove = Outside.service(this,"gus06.file.lnk.remove.insidedir");
		findJar = Outside.service(this,"gus06.app.jarfile");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		File dir = (File) obj;
		File jar = (File) findJar.g();
		return remove.f(new File[]{dir,jar});
	}
	
	public void p(Object obj) throws Exception
	{f(obj);}
}
