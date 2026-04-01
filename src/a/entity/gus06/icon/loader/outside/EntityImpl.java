package a.entity.gus06.icon.loader.outside;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T, R, G, F {

	public String creationDate() {return "20160914";}


	private Service load;
	private File storeDir;
	
	
	public EntityImpl() throws Exception
	{
		load = Outside.service(this,"gus06.icon.loader.dir");
		
		File dir0 = (File) Outside.resource(this,"defaultdir");
		storeDir = new File(dir0,"myicons");
		storeDir.mkdirs();
	}
	
	
	public Object g() throws Exception
	{return storeDir;}
	
	
	public Object t(Object obj) throws Exception
	{return r((String) obj);}
	
	
	public boolean f(Object obj) throws Exception
	{return load.f(new Object[]{storeDir,(String) obj});}
	
	
	public Object r(String key) throws Exception
	{return load.t(new Object[]{storeDir,key});}
}