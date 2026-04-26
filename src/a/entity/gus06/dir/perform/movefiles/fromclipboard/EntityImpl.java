package a.entity.gus06.dir.perform.movefiles.fromclipboard;

import a.framework.*;
import java.io.File;
import java.util.List;

public class EntityImpl implements Entity, P, F {

	public String creationDate() {return "20140918";}


	private Service fromClipboard;
	private Service moveFile;


	public EntityImpl() throws Exception
	{
		fromClipboard = Outside.service(this,"gus.y.clipboard1.files");
		moveFile = Outside.service(this,"gus06.dir.perform.movefile");
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
			moveFile.p(new File[]{f,dir});
		}
		return true;
	}
}
