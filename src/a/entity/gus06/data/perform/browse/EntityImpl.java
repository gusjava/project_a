package a.entity.gus06.data.perform.browse;

import a.framework.*;
import java.io.File;
import java.net.URL;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160819";}


	private Service browseURL;
	
	
	public EntityImpl() throws Exception
	{
		browseURL = Outside.service(this,"gus06.awt.desktop.browse");
	}


	
	public void p(Object obj) throws Exception
	{
		if(obj instanceof String)	{browse((String) obj);return;}
		if(obj instanceof String[])	{browse((String[]) obj);return;}
		if(obj instanceof URL)		{browse((URL) obj);return;}
		if(obj instanceof URL[])	{browse((URL[]) obj);return;}
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private void browse(String s) throws Exception
	{
		browseURL.p(s);
	}
	
	private void browse(String[] ss) throws Exception
	{
		for(String s:ss)
		browseURL.p(s);
	}
	
	private void browse(URL u) throws Exception
	{
		browseURL.p(u);
	}
	
	private void browse(URL[] uu) throws Exception
	{
		for(URL u:uu)
		browseURL.p(u);
	}
}
