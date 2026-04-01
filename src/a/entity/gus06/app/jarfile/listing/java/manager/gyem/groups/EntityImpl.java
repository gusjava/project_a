package a.entity.gus06.app.jarfile.listing.java.manager.gyem.groups;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;


public class EntityImpl implements Entity, R {

	public String creationDate() {return "20140829";}

	public static final String MODULE_START		= "gus06.manager.gus.gyem.";
	public static final String MODULE_END		= ".Module";
	
	

	private Service listing;

	private List listMain;
	private List listModules;

	public EntityImpl() throws Exception
	{
		listing = Outside.service(this,"gus06.app.jarfile.listing.java.manager");
	}
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("mainClasses")) return getMainClasses();
		if(key.equals("modules")) return getModules();
		
		if(key.equals("keys")) return new String[]{"mainClasses","modules"};
		throw new Exception("Unknown key: "+key);
	}
	
	
	private List getMainClasses() throws Exception
	{
		if(listMain==null) init();
		return listMain;
	}
	
	
	private List getModules() throws Exception
	{
		if(listModules==null) init();
		return listModules;
	}
	
	
	
	private void init() throws Exception
	{
		listMain = new ArrayList();
		listModules = new ArrayList();
		
		List list = (List) listing.g();
		for(int i=0;i<list.size();i++)
		{
			String entryName = (String) list.get(i);
			String className = entryToClass(entryName);
				
			if(isModule(entryName))
				listModules.add(buildKey_module(className));
			else listMain.add(buildKey_main(className));
		}
		
		Collections.sort(listMain);
		Collections.sort(listModules);
	}

	
	
	private boolean isModule(String entryName)
	{return entryName.startsWith("gus06/manager/gus/gyem/m");}
	
	
	
	private String buildKey_module(String c)
	{return c.substring(MODULE_START.length(),c.length()-MODULE_END.length());}
	
	
	
	private String buildKey_main(String c)
	{return c.substring(MODULE_START.length(),c.length());}
	
	
	
	
	private String entryToClass(String n) throws Exception
	{
		if(!n.endsWith(".java")) throw new Exception("Invalid java src entry name: "+n);
		n = n.substring(0,n.length()-5);
		return n.replace("/",".");
	}
}
