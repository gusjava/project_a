package a.entity.gus06.java.compiler.v2;

import java.io.File;
import a.framework.*;
import java.io.PrintStream;

public class EntityImpl implements Entity, E, V {

	public String creationDate() {return "20140724";}


	private Service findJavac;
	private Service buildListing;
	private Service handleProcess;
	private Service emptyDir;
	private Service handleOther;
	private Service buildCmd;
	private Service findPrintStream;
	
	private File srcDir;
	private File binDir;
	private File libDir;
	private File jdkDir;
	private F filter;
	
	private File javacFile;
	private File listingFile;
	
	private PrintStream defaultOutput;
	private PrintStream output;
	

	public EntityImpl() throws Exception
	{
		emptyDir = Outside.service(this,"gus06.dir.op.empty");
		findJavac = Outside.service(this,"gus06.java.jdk.javacfile");
		buildListing = Outside.service(this,"gus06.java.compiler.buildlisting");
		buildCmd = Outside.service(this,"gus06.java.compiler.v2.buildcmd");
		handleProcess = Outside.service(this,"gus06.java.compiler.v2.handleprocess");
		handleOther = Outside.service(this,"gus06.java.compiler.v2.handleotherfiles");
		findPrintStream = Outside.service(this,"gus06.find.printstream");
		
		defaultOutput = (PrintStream) Outside.resource(this,"g#gus06.java.compiler.outputanalyzer");
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("srcDir")) {srcDir = (File) obj;return;}
		if(key.equals("binDir")) {binDir = (File) obj;return;}
		if(key.equals("libDir")) {libDir = (File) obj;return;}
		if(key.equals("jdkDir")) {jdkDir = (File) obj;return;}
		if(key.equals("filter")) {filter = (F) obj;return;}
		if(key.equals("output")) {output = (PrintStream) findPrintStream.t(obj);return;}
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	
	public void e() throws Exception
	{
		preparePaths();
		PrintStream out = output!=null ? output : defaultOutput;
		
		if(listingFile.length()==0)
		{
			out.println("No compilation required");
			return;
		}
		
		String[] cmd = buildCmd();
		
		if(fullCompilation()) emptyBin();
		
		ProcessBuilder pb = new ProcessBuilder(cmd);
		pb.redirectErrorStream(true);

		Process pr = pb.start();
		handleProcess.p(new Object[]{pr,out,cmd});
		
		if(fullCompilation()) completeBin();
	}
	
	
	
	
	
	
	private void preparePaths() throws Exception
	{
		if(srcDir==null) throw new Exception("Src directory has not been initialized");
		if(binDir==null) throw new Exception("Bin directory has not been initialized");
		
		srcDir.mkdirs();
		binDir.mkdirs();
		
		if(!srcDir.isDirectory()) throw new Exception("Src directory not found: "+srcDir);
		if(!binDir.isDirectory()) throw new Exception("Bin directory not found: "+binDir);
		
		javacFile = findJavacFile();
		listingFile = findListingFile();
	}
	
	
	private void emptyBin() throws Exception
	{
		emptyDir.p(binDir);
	}
	
	
	private void completeBin() throws Exception
	{
		handleOther.p(new File[]{srcDir,binDir});
	}
	
	
	
	private String[] buildCmd() throws Exception
	{
		return (String[]) buildCmd.t(new File[]{javacFile,libDir,srcDir,binDir,listingFile});
	}
	
	private File findListingFile() throws Exception
	{
		return (File) buildListing.t(new Object[]{srcDir,filter});
	}
	
	private File findJavacFile() throws Exception
	{
		if(jdkDir!=null && jdkDir.isDirectory())
			return (File) findJavac.t(jdkDir); 
		return (File) findJavac.g();
	}
	
	private boolean fullCompilation()
	{
		return filter==null;
	}
}
