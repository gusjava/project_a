package a.entity.gus06.dir.listing0.names;

import java.io.File;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151106";}


	public Object t(Object obj) throws Exception
	{
		File[] f = toListing(obj);
		if(f==null) return new String[0];
		
		String[] n = new String[f.length];
		for(int i=0;i<f.length;i++) n[i] = f[i].getName();
		
		return n;
	}
	
	
	
	private File[] toListing(Object obj) throws Exception
	{
		if(obj instanceof File[]) return (File[]) obj;
		if(obj instanceof File) return ((File) obj).listFiles();
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}