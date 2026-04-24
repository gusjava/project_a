package a.entity.gus06.app.restart_newversion;

import java.io.File;

import a.framework.*;
import java.io.PrintStream;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20251120";}


	private Service findArgs;
	private Service appJar;
	private Service javaExe;
	private Service exit;
	
	private File store;
	private File batchFile;

	public EntityImpl() throws Exception
	{
		findArgs = Outside.service(this,"gus.y.app1.argsline");
		appJar = Outside.service(this,"gus06.app.jarfile");
		javaExe = Outside.service(this,"gus06.java.dir.bin.javaexe");
		exit = Outside.service(this,"gus06.app.execute.exit");
		
		store = (File) Outside.resource(this,"defaultdir");
		batchFile = new File(store, "restart.bat");
	}
	
	public void p(Object obj) throws Exception
	{
		if(obj instanceof File) handleFile((File) obj);
		else if(obj instanceof Object[]) handleArray((Object[]) obj);
		else throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private void handleFile(File jar0) throws Exception
	{
		String args = (String) findArgs.g();
		performRestart(jar0, args);
	}
	
	private void handleArray(Object[] o) throws Exception
	{
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		File jar0 = (File) o[0];
		String args = (String) o[1];
		performRestart(jar0, args);
	}
	
	private void performRestart(File jar0, String args) throws Exception
	{
		File jar = (File) appJar.g();
		File exe = (File) javaExe.g();
		
		String pathJar = p(jar);
		String pathJar0 = p(jar0);
		String pathExe = p(exe);
		
		PrintStream ps = new PrintStream(batchFile);
		ps.println("@echo off");
		ps.println(":wait");
		ps.println("del "+pathJar+" >nul 2>&1");
		ps.println("if exist "+pathJar+" (");
		ps.println("timeout /t 1 /nobreak >nul");
		ps.println("goto wait");
		ps.println(")");
		ps.println("move /y "+pathJar0+" "+pathJar);
		
		if(args!=null) ps.println(pathExe+" -jar "+pathJar+" "+args);
		else ps.println(pathExe+" -jar "+pathJar);
		ps.close();
		
		Runtime.getRuntime().exec("cmd /c start /b \"\" " + p(batchFile));
		
		exit.e();
		System.exit(0);
	}
	
	private String p(File file)
	{return "\""+file.getAbsolutePath()+"\"";}
}