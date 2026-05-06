package a.entity.gus06.java.compiler.manager;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20141110";}


	private Service compiler;
	private Service readFile;
	private Service extractPackage;
	private Service initFramework;
	

	public EntityImpl() throws Exception
	{
		compiler = Outside.service(this,"gus06.java.compiler1");
		readFile = Outside.service(this,"gus.x.file.string.read.v1");
		extractPackage = Outside.service(this,"gus06.java.srccode.extract.package1");
		initFramework = Outside.service(this,"gus06.app.jarfile.extract1.framework2");
	}
	
	
	public void e() throws Exception
	{
		initFramework.e();
		compiler.p(new Filter());
	}



	private class Filter implements F
	{
		public boolean f(Object obj) throws Exception
		{
			File file = (File) obj;
			String src = (String) readFile.t(file);
			String package1 = (String) extractPackage.t(src);

			if(package1.equals("gus06.framework")) return true;
			if(package1.equals("gus06.manager.")) return true;

			return false;
		}
	}
}
