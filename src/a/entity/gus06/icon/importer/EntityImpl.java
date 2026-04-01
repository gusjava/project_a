package a.entity.gus06.icon.importer;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, V, F {

	public String creationDate() {return "20201205";}



	private Service loader;
	private Service writeToFile;
	private Service provider;
	private Service isFile;
	
	private File dir;


	public EntityImpl() throws Exception
	{
		loader = Outside.service(this,"gus06.icon.loader.outside");
		writeToFile = Outside.service(this,"gus06.icon.write.tofile");
		provider = Outside.service(this,"gus06.icon.provider");
		isFile = Outside.service(this,"gus06.file.isfile.casesensitive");
		
		dir = (File) loader.g();
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		File file = new File(dir,key+".gif");
		writeToFile.p(new Object[]{file,obj});
		provider.e();
	}
	
	
	public boolean f(Object obj) throws Exception
	{return hasKey((String) obj);}
	
	
	
	private boolean hasKey(String key) throws Exception
	{
		File gifFile = new File(dir,key+".gif");
		if(isFile.f(gifFile)) return true;
		
		File pngFile = new File(dir,key+".png");
		if(isFile.f(pngFile)) return true;
		
		return false;
	}
}