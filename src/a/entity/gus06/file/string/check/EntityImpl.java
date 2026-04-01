package a.entity.gus06.file.string.check;

import java.io.File;
import java.io.FileFilter;
import a.framework.*;

public class EntityImpl implements Entity, F, G, FileFilter {

	public String creationDate() {return "20150822";}
	

	private Service isOfType;

	public EntityImpl() throws Exception
	{isOfType = Outside.service(this,"gus06.file.filter.mime.isoftype.text.plain");}

	
	public boolean f(Object obj) throws Exception
	{return isOfType.f(obj);}
	
	
	public Object g() throws Exception
	{return this;}
	
	
	
	public boolean accept(File f)
	{
		try{return f(f);}
		catch(Exception e) {Outside.err(this,"accept(File)",e);}
		return false;
	}
}
