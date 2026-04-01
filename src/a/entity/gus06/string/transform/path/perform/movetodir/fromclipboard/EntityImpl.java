package a.entity.gus06.string.transform.path.perform.movetodir.fromclipboard;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250503";}


	private Service perform;
	private Service clipboard;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.dirfile.op.movetodir.autorename");
		clipboard = Outside.service(this,"gus06.clipboard.access.file.asdir");
	}
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		
		File dir = (File) clipboard.g();
		if(dir==null) return s+"\n->DIR NOT FOUND...";
		
		String[] n = s.split("\n",-1);
		
		for(int i=0;i<n.length;i++) handle(n[i], dir);
		return s;
	}
	
	
	
	private void handle(String line, File dir)
	{
		try
		{
			File file = new File(line.trim());
			if(file.exists()) perform.p(new Object[]{file, dir});
		}
		catch(Exception e)
		{Outside.err(this,"handle(String, File)",e);}
	}
}