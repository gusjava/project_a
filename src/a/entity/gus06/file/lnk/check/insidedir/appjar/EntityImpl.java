package a.entity.gus06.file.lnk.check.insidedir.appjar;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20180309";}


	private Service check;
	private Service findJar;

	public EntityImpl() throws Exception
	{
		check = Outside.service(this,"gus06.file.lnk.check.insidedir");
		findJar = Outside.service(this,"gus.x.app.location.asjar");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		File dir = (File) obj;
		File jar = (File) findJar.g();
		return check.f(new File[]{dir,jar});
	}
}
