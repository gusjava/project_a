package a.entity.gus.y.logger1.printinfos1;

import java.io.File;
import java.util.Date;
import java.util.StringJoiner;

import a.framework.*;

public class EntityImpl implements Entity, E, P {
	public String creationDate() {return "20231212";}
	
	private Service logger;
	private Service fileDisplay;
	private Service findRootDir;
	private Service findSrcDir;
	private Service findAppLocation;
	private Service findAppPID;
	private Service findDev;
	private Service findJDK;
	
	private Class launchClass;
	private String launchArgs[];
	private Date launchDate;
	private String coreId;
	private String confidId;

	public EntityImpl() throws Exception {
		logger = Outside.service(this, "logger");
		fileDisplay = Outside.service(this, "gus.x.file.getdisplay1");
		findRootDir = Outside.service(this, "gus.y.paths1.rootdir");
		findSrcDir = Outside.service(this, "gus.y.srcroot1");
		findAppLocation = Outside.service(this, "gus.x.app.location");
		findAppPID = Outside.service(this, "gus.x.app.pid");
		findDev = Outside.service(this, "gus.y.srcroot1.dev");
		findJDK = Outside.service(this, "gus.y.jdk1.dir.jdkdirs.latest");
		
		launchClass = (Class) Outside.resource(this, "launch.class");
		launchArgs = (String[]) Outside.resource(this, "launch.args");
		launchDate = (Date) Outside.resource(this, "launch.date");
		coreId = (String) Outside.resource(this, "core.id");
		confidId = (String) Outside.resource(this, "configid");
		
	}
	
	public void e() throws Exception {
		printInfos(logger);
	}

	public void p(Object obj) throws Exception {
		printInfos((P) obj);
	}
	
	private void printInfos(P p) throws Exception {
		File appLoc = (File) findAppLocation.g();
		String rootDirDisplay = (String) findRootDir.r("display");
		String srcDirDisplay = (String) findSrcDir.r("display");
		String devIdDisplay = (String) findDev.r("display");
		String appPID = (String) findAppPID.g();
		String javaHome = System.getProperty("java.home");
		String javaVersion = System.getProperty("java.runtime.version");
		File jdkDir = (File) findJDK.g();

		p.p(new Object[] {this, "appLocation=" + fileDisplay.t(appLoc)});
		p.p(new Object[] {this, "appPID=" + appPID});
		p.p(new Object[] {this, "javaHome=" + javaHome});
		p.p(new Object[] {this, "javaVersion=" + javaVersion});
		p.p(new Object[] {this, "launchClass=" + launchClass.getName()});
		p.p(new Object[] {this, "launchArgs=" + argsToLine()});
		p.p(new Object[] {this, "launchDate=" + launchDate});
		p.p(new Object[] {this, "coreId=" + coreId});
		p.p(new Object[] {this, "confidId=" + confidId});
		p.p(new Object[] {this, "rootDir=" + rootDirDisplay});
		p.p(new Object[] {this, "srcDir=" + srcDirDisplay});
		p.p(new Object[] {this, "devId=" + devIdDisplay});
		p.p(new Object[] {this, "jdk=" + fileDisplay.t(jdkDir)});
	}
	
	private String argsToLine() {
		StringJoiner sj = new StringJoiner(" ");
		for (String arg : launchArgs)
			sj.add(arg);
		return sj.toString();
	}
}
