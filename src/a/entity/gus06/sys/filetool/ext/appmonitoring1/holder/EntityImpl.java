package a.entity.gus06.sys.filetool.ext.appmonitoring1.holder;

import a.framework.*;
import javax.swing.JComponent;
import java.util.Map;
import java.io.File;

public class EntityImpl implements Entity, I, P, E {

	public String creationDate() {return "20161107";}
	
	public static final String KEY_STRUCT = "struct";
	
	
	private Service findRoot;
	private Service tabHolder;

	private Service threadViewer;
	private Service exceptionViewer;
	private Service actionsViewer;
	private Service systemViewer;
	private Service jreViewer;
	private Service mainViewer;
	private Service entitiesViewer;
	private Service infoViewer;
	private Service appJarViewer;
	private Service appIconViewer;
	private Service appServerViewer;
	
	
	
	private Map map;
	private File root;
	

	public EntityImpl() throws Exception
	{
		findRoot = Outside.service(this,"gus06.sys.filetool.findroot");
		tabHolder = Outside.service(this,"*gus06.swing.tabbedpane.holder1");
	
		threadViewer = Outside.service(this,"*gus06.thread.gui.viewer");
		exceptionViewer = Outside.service(this,"*gus06.exception.gui.viewer");
		actionsViewer = Outside.service(this,"*gus06.appli.gusclient1.gui.space.monitoring.actions");
		systemViewer = Outside.service(this,"*gus06.appli.gusclient1.gui.space.monitoring.system");
		jreViewer = Outside.service(this,"*gus06.appli.gusclient1.gui.space.monitoring.jre");
		mainViewer = Outside.service(this,"*gus06.appli.gusclient1.gui.space.monitoring.main");
		entitiesViewer = Outside.service(this,"*gus06.appli.gusclient1.gui.space.monitoring.entities");
		infoViewer = Outside.service(this,"*gus06.app.info.gui.viewer");
		appJarViewer = Outside.service(this,"*gus06.app.jarfile.gui.viewer");
		appIconViewer = Outside.service(this,"*gus06.app.icon.gui.viewer1");
		appServerViewer = Outside.service(this,"*gus06.app.server.gui.viewer1");
	}
	
	
	public Object i() throws Exception
	{return tabHolder.i();}
	
	
	
	public void p(Object obj) throws Exception
	{
		map = (Map) obj;
		root = (File) findRoot.t(map);
		
		String struct = get(KEY_STRUCT);
		if(struct==null) {buildDefault();return;}
		
		String[] nn = struct.split(";");
		for(String n:nn) build(n);
	}
	
	
	public void e() throws Exception
	{
		buildDefault();
	}
	
	
	private void buildDefault() throws Exception
	{
		tabHolder.v("Threads",threadViewer.i());
		tabHolder.v("Exceptions",exceptionViewer.i());
		tabHolder.v("Actions",actionsViewer.i());
		tabHolder.v("System",systemViewer.i());
		tabHolder.v("JRE",jreViewer.i());
		tabHolder.v("Main",mainViewer.i());
		tabHolder.v("Entities",entitiesViewer.i());
		tabHolder.v("Infos",infoViewer.i());
		tabHolder.v("App jar",appJarViewer.i());
		tabHolder.v("App icons",appIconViewer.i());
		tabHolder.v("Socket Servers",appServerViewer.i());
	}
	
	
	private void build(String id) throws Exception
	{
		if(id.equals("threads"))		addTab(id,"Threads",threadViewer);
		else if(id.equals("exceptions"))	addTab(id,"Exceptions",exceptionViewer);
		else if(id.equals("actions"))		addTab(id,"Actions",actionsViewer);
		else if(id.equals("system"))		addTab(id,"System",systemViewer);
		else if(id.equals("system.out"))	addTab(id,"System.out",(I) systemViewer.r("sysoutViewer"));
		else if(id.equals("system.err"))	addTab(id,"System.err",(I) systemViewer.r("syserrViewer"));
		else if(id.equals("system.prop"))	addTab(id,"System.prop",(I) systemViewer.r("syspropViewer"));
		else if(id.equals("jre"))		addTab(id,"JRE",jreViewer);
		else if(id.equals("main"))		addTab(id,"Main",mainViewer);
		else if(id.equals("entities"))		addTab(id,"Entities",entitiesViewer);
		else if(id.equals("infos"))		addTab(id,"Infos",infoViewer);
		else if(id.equals("appjar"))		addTab(id,"App jar",appJarViewer);
	}
	
	
	private void addTab(String id, String display0, I gui) throws Exception
	{
		String display = get(id+".display");
		if(display==null) display = display0;
		tabHolder.v(display,gui.i());
	}
	
	
	private String get(String key) throws Exception
	{
		if(map==null) return null;
		if(!map.containsKey(key)) return null;
		return (String) map.get(key);
	}
}