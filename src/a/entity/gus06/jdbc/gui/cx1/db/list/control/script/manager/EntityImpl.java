package a.entity.gus06.jdbc.gui.cx1.db.list.control.script.manager;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, G, R {

	public String creationDate() {return "20231015";}

	private Service dirToSet;
	private Service buildP;
	
	private File dir;

	public EntityImpl() throws Exception
	{
		dirToSet = Outside.service(this,"gus06.dir.children.dirtoset.name0");
		buildP = Outside.service(this,"gus06.sys.script1.build1.p");
		
		File dir0 = (File) Outside.resource(this,"defaultdir");
		dir = new File(dir0,"scripts_h");
		dir.mkdirs();
	}
	
	
	public Object g() throws Exception
	{
		return dirToSet.t(dir);
	}
	
	public Object r(String key) throws Exception
	{
		if(key.equals("dir")) return dir;
		if(key.equals("keys")) return new String[]{"dir"};
		
		if(key.startsWith("file:")) return file(key.substring(5));
		if(key.startsWith("p:")) return buildP(key.substring(2));
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	private File file(String key) throws Exception
	{
		File f = new File(dir,key+".gus");
		return f.isFile() && f.length()>0 ? f : null;
	}
	
	private P buildP(String key) throws Exception
	{
		File f = file(key);
		if(f==null) return null;
		return (P) buildP.t(f);
	}
}