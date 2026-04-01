package a.entity.gus06.app.jarfile;

import java.io.File;

import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20140703";}

	
	private File location;
	
	
	public Object g() throws Exception
	{
		if(location==null) location = findLocation();
		
		if(location.isDirectory()) return null;
		return location;
	}
	
	private File findLocation() throws Exception
	{return new File(Outside.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());}
}
