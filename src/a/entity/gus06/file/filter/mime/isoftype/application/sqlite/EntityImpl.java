package a.entity.gus06.file.filter.mime.isoftype.application.sqlite;

import java.io.File;
import java.io.FileFilter;
import a.framework.*;

public class EntityImpl implements Entity, F, G, FileFilter {

	public String creationDate() {return "20250723";}
	
	public static final String TYPE1 = "application/x-sqlite3";
	public static final String TYPE2 = "application/vnd.sqlite3";

	private Service checkMime;

	public EntityImpl() throws Exception
	{checkMime = Outside.service(this,"gus06.file.mime.tika.check.isoftype");}

	
	public boolean f(Object obj) throws Exception
	{
		if(checkMime.f(new Object[]{obj,TYPE1})) return true;
		if(checkMime.f(new Object[]{obj,TYPE2})) return true;
		return false;
	}
	
	
	public Object g() throws Exception
	{return this;}
	
	
	
	public boolean accept(File f)
	{
		try{return f(f);}
		catch(Exception e) {Outside.err(this,"accept(File)",e);}
		return false;
	}
}