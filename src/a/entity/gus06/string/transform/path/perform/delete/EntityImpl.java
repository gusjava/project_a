package a.entity.gus06.string.transform.path.perform.delete;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250503";}


	private Service perform;


	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.dirfile.op.delete");
		
	}

	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		String[] n = s.split("\n",-1);
		
		for(int i=0;i<n.length;i++) handle(n[i]);
		return s;
	}
	
	
	
	private void handle(String line)
	{
		try
		{
			File file = new File(line.trim());
			if(file.exists()) perform.p(file);
		}
		catch(Exception e)
		{Outside.err(this,"handle(String)",e);}
	}
}