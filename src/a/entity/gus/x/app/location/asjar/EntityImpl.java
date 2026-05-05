package a.entity.gus.x.app.location.asjar;

import java.io.File;

import a.framework.*;

public class EntityImpl implements Entity, G {
	public String creationDate() {return "20140703";}

	private File location;
	
	public Object g() throws Exception
	{
		if(location==null) location = find();
		
		if(location.isDirectory()) return null;
		return location;
	}
	
	private File find() throws Exception
	{return new File(Outside.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());}
}
