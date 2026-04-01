package a.entity.gus06.app.jarfile.extract1.framework;

import java.io.File;

import a.framework.*;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20140801";}

	public static final String START = "gus06/framework/";
	
	
	private Service extract;
	private F filter;
	
	
	public EntityImpl() throws Exception
	{
		extract = Outside.service(this,"gus06.app.jarfile.extract1");
		filter = new F() {
			public boolean f(Object obj) throws Exception
			{return isValid((String) obj);}
		};
	}
	
	
	public void e() throws Exception
	{extract.p(filter);}
	
	
	private boolean isValid(String name)
	{return name.startsWith(START);}
}
