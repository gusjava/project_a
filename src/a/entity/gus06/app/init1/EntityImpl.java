package a.entity.gus06.app.init1;

import a.framework.*;

public class EntityImpl implements Entity {

	public String creationDate() {return "20150626";}


	private Service writePid;
	private Service initSysprop;
	private Service initJar;
	private Service initDll;
	private Service initLog;
	private Service modifyPath;
	private Service exceptionHandler;
	private Service initDebugGui;


	public EntityImpl() throws Exception
	{
		writePid = Outside.service(this,"gus06.app.init.writepid");
		initSysprop = Outside.service(this,"gus06.system.prop.init");
		initJar = Outside.service(this,"gus06.app.init.jar");
		initDll = Outside.service(this,"gus06.app.init.dll");
		initLog = Outside.service(this,"gus06.app.init.log");
		modifyPath = Outside.service(this,"gus06.system.javalibrarypath.modify.init");
		exceptionHandler = Outside.service(this,"gus06.exception.uncaughtexceptionhandler.init");
		initDebugGui = Outside.service(this,"gus06.debug.init.displaygui");
	}
}
