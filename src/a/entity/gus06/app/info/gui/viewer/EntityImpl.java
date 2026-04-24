package a.entity.gus06.app.info.gui.viewer;

import a.framework.*;
import javax.swing.JComponent;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Map;
import java.util.Date;
import java.io.File;
import java.text.SimpleDateFormat;
import javax.swing.JLabel;


public class EntityImpl implements Entity, I {

	public String creationDate() {return "20140818";}

	public static final long LAPSE = 900;


	public static final String KEY_CURRENTTIME = "Current time";
	public static final String KEY_STARTTIME = "Start time";
	public static final String KEY_JARPATH = "Jar path";
	public static final String KEY_ARGSLINE = "Args line";
	public static final String KEY_PID = "Runtime pid";
	public static final String KEY_JAVAVER = "Java version";
	public static final String KEY_JAVAHOME = "Java home";
	public static final String KEY_AUTHOR = "Author";
	public static final String KEY_TITLE = "Title";
	public static final String KEY_VERSION = "Version";
	public static final String KEY_MANAGERID = "Manager id";
	public static final String KEY_ENTITYNB = "Entity number";
	public static final String KEY_BUILDID = "Build id";
	public static final String KEY_BUILDTIME = "Build time";
	public static final String KEY_JARTIME = "Jar time";
	public static final String KEY_JARMD5 = "Jar md5";
	public static final String KEY_JVMMEMMAX = "JVM mem max";
	public static final String KEY_JVMMEMTOTAL = "JVM mem total";
	
	public static final String PROP_BUILDID = "jar.buildid";
	public static final String PROP_BUILDTIME = "jar.buildtime";
	public static final String PROP_AUTHOR = "app.author.name";
	public static final String PROP_VERSION = "app.version";
	public static final String PROP_TITLE = "app.title";
	
	
	
	
	private Service formPanel;
	
	private Service getJarFile;
	private Service getJarMd5;
	private Service getArgsLine;
	private Service getStartTime;
	private Service getPid;
	private Service getJarTime;
	private Service getEntityNb;
	
	private Map prop;
	private String managerId;
	
	private Service getTimer;
	private Timer timer;
	private TimerTask task;
	
	private JLabel labelNow;

	
	
	public EntityImpl() throws Exception
	{
		formPanel = Outside.service(this,"*gus06.swing.panel.formpanel.panel1");
		
		getJarFile = Outside.service(this,"gus06.app.jarfile");
		getJarMd5 = Outside.service(this,"gus06.app.jarfile.md5");
		getArgsLine = Outside.service(this,"gus.y.app1.argsline");
		getStartTime = Outside.service(this,"gus06.app.starttime");
		getPid = Outside.service(this,"gus06.app.pid");
		getJarTime = Outside.service(this,"gus06.app.outside.lastmodified.timestamp");
		getEntityNb = Outside.service(this,"gus06.app.jarfile.listing.entities.nb");
		
		prop = (Map) Outside.resource(this,"props");
		managerId = (String) Outside.resource(this,"core.id");
		
		getTimer = Outside.service(this,"gus06.time.timer.unique");
		timer = (Timer) getTimer.g();
		
		String buildId = get(PROP_BUILDID);
		String buildTime = get(PROP_BUILDTIME);
		String author = get(PROP_AUTHOR);
		String version = get(PROP_VERSION);
		String title = get(PROP_TITLE);
		
		String javaVer = System.getProperty("java.runtime.version");
		String javaHome = System.getProperty("java.home");
		
		String jvmMemMax = ""+Runtime.getRuntime().maxMemory();
		String jvmMemTotal = ""+Runtime.getRuntime().totalMemory();

		File jarFile = (File) getJarFile.g();
		String jarMd5 = (String) getJarMd5.g();
		String argsLine = (String) getArgsLine.g();
		String startTime = (String) getStartTime.g();
		String jarTime = (String) getJarTime.g();
		String pid = (String) getPid.g();
		String entityNb = (String) getEntityNb.g();
		String jarPath = jarFile!=null ? jarFile.getAbsolutePath() : "";
		
		
		put(KEY_TITLE,title);
		put(KEY_VERSION,version);
		put(KEY_AUTHOR,author);
		
		putSep();
		
		put(KEY_BUILDID,buildId);
		put(KEY_MANAGERID,managerId);
		put(KEY_ENTITYNB,entityNb);
		put(KEY_JARMD5,jarMd5);
		
		putSep();
		
		labelNow = label(KEY_CURRENTTIME);
		put(KEY_STARTTIME,startTime);
		put(KEY_JARTIME,jarTime);
		put(KEY_BUILDTIME,buildTime);
		
		putSep();
		
		put(KEY_JARPATH,jarPath);
		put(KEY_ARGSLINE,argsLine);
		put(KEY_JAVAHOME,javaHome);
		put(KEY_JAVAVER,javaVer);
		put(KEY_PID,pid);
		
		putSep();
		
		put(KEY_JVMMEMMAX,jvmMemMax);
		put(KEY_JVMMEMTOTAL,jvmMemTotal);
		
		task = new TimerTask() {public void run() {updateGui();}};
		timer.schedule(task,new Date(),LAPSE);
		updateGui();
	}
	
	
	public Object i() throws Exception
	{return formPanel.i();}
	
	
	
	
	
	private void put(String key, String value) throws Exception
	{formPanel.v(key,value);}
	
	private void putSep() throws Exception
	{formPanel.p("sep");}
	
	private JLabel label(String key) throws Exception
	{return (JLabel) formPanel.r(key);}
	
	
	
	private String get(String key)
	{
		if(!prop.containsKey(key)) return "?";
		return (String) prop.get(key);
	}
	
	
	
	private void updateGui()
	{
		labelNow.setText(now());
	}
	
	
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
	private String now() {return sdf.format(new Date());}
}
