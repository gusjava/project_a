package a.entity.gus06.appli.gusclient1.execute.project.template.autoconfig1;

import a.framework.*;
import java.util.HashMap;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20150615";}


	private Service manager;
	private Service setPPath;
	private Service setProp;
	private Service updateDll;
	private Service updateJar;


	public EntityImpl() throws Exception
	{
		manager = Outside.service(this,"gus06.appli.gusclient1.project.manager");
		setPPath = Outside.service(this,"gus06.appli.gusclient1.project.config.set.p_path");
		setProp = Outside.service(this,"gus06.appli.gusclient1.project.config.setprop");
		updateDll = Outside.service(this,"gus06.appli.gusclient1.project.config.update.dll_location");
		updateJar = Outside.service(this,"gus06.appli.gusclient1.project.config.update.jar_location");
	}
	
	
	public void e() throws Exception
	{
		String id = (String) manager.g();
		if(invalid(id)) return;
			
		path("root","<user.home>\\.gus06\\app_"+id);
		path("path.gyem.apidir","<path.rootdir>\\api");
		path("path.gyem.pooldir","<path.rootdir>\\pool");
		path("path.logdir","<path.rootdir>\\log");
		path("path.jardir","<path.rootdir>\\api");
		path("path.dlldir","<path.rootdir>\\dll");
		
		prop("system.librarypath","path.dlldir");
		
		prop("jar.buildid",id);
		prop("app.name",id);
		
		updateDll.e();
		updateJar.e();
	}
	
	
	
	private void path(String key, String value) throws Exception
	{setPPath.v(key,value);}
	
	private void prop(String key, String value) throws Exception
	{setProp.v(key,value);}
	
	
	
	private boolean invalid(String s)
	{return s==null || s.equals("");}
}
