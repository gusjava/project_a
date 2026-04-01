package a.entity.gus06.web.check.online;

import a.framework.*;
import java.net.URL;
import java.io.IOException;


public class EntityImpl implements Entity, F {

	public String creationDate() {return "20170404";}

	public static final String URLTEST = "http://www.google.com";
	
	
	
	private URL urlTest;
	
	public EntityImpl() throws Exception
	{
		urlTest = new URL(URLTEST);
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		try
		{
			urlTest.openConnection().connect();
			return true;
		}
		catch(IOException e){}
		return false;
	}
}
