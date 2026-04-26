package a.entity.gus06.dir.perform.linkgusfiles.fromclipboard;

import a.framework.*;
import java.io.File;
import java.util.List;

public class EntityImpl implements Entity, P, F {

	public String creationDate() {return "20250625";}


	private Service fromClipboard;
	private Service linkFile;


	public EntityImpl() throws Exception
	{
		fromClipboard = Outside.service(this,"gus.y.clipboard1.files");
		linkFile = Outside.service(this,"gus06.dir.perform.linkgusfile");
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
			linkFile.p(new File[]{f,dir});
		}
		return true;
	}
}