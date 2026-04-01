package a.entity.gus06.string.lines.browse.openurl;

import a.framework.*;
import java.net.URL;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20210512";}


	private Service browseUrl;


	public EntityImpl() throws Exception
	{
		browseUrl = Outside.service(this,"gus06.awt.desktop.browse");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		String line = (String) obj;
		try
		{
			browseUrl.p(new URL(line));
			return true;
		}
		catch(Exception e)
		{return false;}
	}
}
