package a.entity.gus06.appli.gusexplorer.execute.tools.web.show.browser;

import a.framework.*;
import java.net.URL;
import java.net.MalformedURLException;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20190710";}


	private Service show;
	private Service clipboard;
	
	public EntityImpl() throws Exception
	{
		show = Outside.service(this,"*gus06.swt.webbrowser.show");
		clipboard = Outside.service(this,"gus.x.clipboard.string");
	}
	
	public void e() throws Exception
	{
		String s = (String) clipboard.g();
		if(!isValidLocation(s)) s = "https://www.google.fr/";
		show.p(s);
	}
	
	
	private boolean isValidLocation(String s)
	{
		try{new URL(s);}
		catch(MalformedURLException e)
		{return false;}
		return true;
	}
}
