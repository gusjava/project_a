package a.entity.gus06.file.perform.generate.jar.from.gusscript.prop;

import a.framework.*;
import java.util.Properties;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20161015";}


	private Service now;

	public EntityImpl() throws Exception
	{
		now = Outside.service(this,"gus06.time.now");
	}
	
	
	public Object g() throws Exception
	{
		Properties prop = new Properties();
		
		prop.put("path.dlldir",		"<path.rootdir>\\dll");
		prop.put("path.gyem.apidir",	"<path.rootdir>\\api");
		prop.put("path.gyem.pooldir",	"<path.rootdir>\\pool");
		prop.put("path.jardir",		"<path.rootdir>\\api");
		prop.put("path.logdir",		"<path.rootdir>\\log");
		prop.put("root",		"<user.home>\\.gus06\\app_scriptholder");
		
		prop.put("start.0",		"gus.app.init.writepid");
		prop.put("start.1",		"gus.app.init.jar");
		prop.put("start.2",		"gus.app.init.dll");
		prop.put("start.3",		"gus.app.init.log");
		prop.put("start.4",		"gus.jdbc.loaddriver.mysql");
		prop.put("start.5",		"gus.system.javalibrarypath.modify.init");
		prop.put("start.6",		"gus.exception.uncaughtexceptionhandler.init");
		prop.put("start.7",		"gus.sys.script1.main.frominside.init");
		
		prop.put("app.type",		"nogui");
		prop.put("jar.buildid",		"scriptholder");
		prop.put("jar.buildtime",	""+now.g());
		prop.put("system.librarypath",	"path.dlldir");
		
		prop.put("cust.cust.t.rb.icon",			"gus.maincust.icon.rb");
		prop.put("cust.m092.p.mainframe.customizer",	"gus.maincust.customizeframe");
		
		return prop;
	}
}
