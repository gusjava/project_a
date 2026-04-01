package a.entity.gus06.string.transform.url.perform.browse;

import a.framework.*;
import java.io.File;
import java.net.URL;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250503";}


	private Service perform;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.data.perform.browse");
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
			URL url = new URL(line.trim());
			perform.p(url);
		}
		catch(Exception e)
		{Outside.err(this,"handle(String)",e);}
	}
}