package a.entity.gus06.dir.perform.copyfiles.fromclipboard;

import a.framework.*;
import java.io.File;
import java.util.List;

public class EntityImpl implements Entity, P, F {

	public String creationDate() {return "20140918";}


	private Service fromClipboard;
	private Service copyFile;


	public EntityImpl() throws Exception
	{
		fromClipboard = Outside.service(this,"gus06.clipboard.access.listfiles");
		copyFile = Outside.service(this,"gus06.dir.perform.copyfile");
	}
	
	
	public void p(Object obj) throws Exception
	{f(obj);}
	
	
	public boolean f(Object obj) throws Exception
	{
		File dir = (File) obj;
		
		List files = (List) fromClipboard.g();
		if(files==null || files.isEmpty()) return false;
		
		for(Object o:files)
		{
			File f = (File) o;
			copyFile.p(new File[]{f,dir});
		}
		return true;
	}
}
