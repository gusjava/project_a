package a.entity.gus06.file.properties.check;

import java.io.File;
import java.io.FileFilter;
import a.framework.*;

public class EntityImpl implements Entity, F, G, FileFilter {

	public String creationDate() {return "20160412";}
	

	private Service isType;

	public EntityImpl() throws Exception
	{isType = Outside.service(this,"gus06.file.filter.mime.istype.text.properties");}

	
	public boolean f(Object obj) throws Exception
	{return isType.f(obj);}
	
	
	public Object g() throws Exception
	{return this;}
	
	
	
	public boolean accept(File f)
	{
		try{return f(f);}
		catch(Exception e) {Outside.err(this,"accept(File)",e);}
		return false;
	}
}
