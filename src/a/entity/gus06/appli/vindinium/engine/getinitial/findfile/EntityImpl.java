package a.entity.gus06.appli.vindinium.engine.getinitial.findfile;

import java.io.File;
import a.framework.*;

public class EntityImpl implements Entity, G, P {

	public String creationDate() {return "20170923";}


	private File storeDir;
	private String name;
	
	public EntityImpl() throws Exception
	{
		storeDir = (File) Outside.resource(this,"defaultdir");
		if(storeDir==null) throw new Exception("StoreDir is null");
	}

	
	public void p(Object obj) throws Exception
	{name = (String) obj;}

	
	public Object g() throws Exception
	{
		if(name==null) return randomFile();
		return new File(storeDir,name+".txt");
	}
	
	
	private File randomFile()
	{
		File[] f = storeDir.listFiles();
		if(f==null || f.length==0) return null;
		int n = (int)(Math.random()*f.length);
		return f[n];
	}
}
