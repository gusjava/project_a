package a.entity.gus06.app.persister1.data.file;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, V, R {

	public String creationDate() {return "20141122";}


	private Service persister;


	public EntityImpl() throws Exception
	{persister = Outside.service(this,"gus06.app.persister1");}
	
	
	
	public Object r(String key) throws Exception
	{return stringToFile((String) persister.r(key));}
	
	
	public void v(String key, Object obj) throws Exception
	{persister.v(key,fileToString((File) obj));}
	
	
	
	private String fileToString(File f)
	{
		if(f==null) return null;
		return f.getAbsolutePath();
	}
	
	
	private File stringToFile(String s)
	{
		if(s==null) return null;
		return new File(s);
	}
		
}
