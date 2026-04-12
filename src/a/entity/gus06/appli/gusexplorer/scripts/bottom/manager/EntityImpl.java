package a.entity.gus06.appli.gusexplorer.scripts.bottom.manager;

import a.framework.*;
import java.util.List;
import java.util.Arrays;
import java.io.File;

public class EntityImpl extends S1 implements Entity, R, P, G {

	public String creationDate() {return "20251202";}


	private Service buildE;
	private Service fileToName0;
	private Service listing;
	private Service trigger;

	private File dir;
	
	
	public EntityImpl() throws Exception
	{
		buildE = Outside.service(this,"gus06.sys.script1.build1.e");
		fileToName0 = Outside.service(this,"gus06.dir.listing0.names0");
		listing = Outside.service(this,"gus06.dir.listing0.ext.gus");
		trigger = Outside.service(this,"gus06.support1.cust.trigger.s10");
		
		File dir0 = (File) Outside.resource(this,"defaultdir");
		
		dir = new File(dir0,"scripts_bottom");
		dir.mkdirs();
		
		trigger.p(this);
	}
	
	
	public Object g() throws Exception
	{
		String[] l = (String[]) fileToName0.t(listing.t(dir));
		return Arrays.asList(l);
	}
	
	
	public void p(Object obj) throws Exception
	{
		String name = (String) obj;
		File f = file(name);
		if(!f.exists()) throw new Exception("Script file not found: "+f);
		
		E execute = (E) buildE.t(f);
		execute.e();
	}
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("dir")) return dir;
		if(key.equals("keys")) return new String[]{"dir"};
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	private File file(String name)
	{return new File(dir,name+".gus");}
}