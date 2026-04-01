package a.entity.gus06.java.compiler.v1;

import java.io.File;
import a.framework.*;
import java.io.PrintStream;

public class EntityImpl implements Entity, E, V {

	public String creationDate() {return "20140722";}


	private Service findJavac;
	private Service buildListing;
	private Service debugScripts;
	private Service handleProcess;
	
	private File srcDir;
	private File binDir;
	private File libDir;
	private F filter;
	private PrintStream out;
	

	public EntityImpl() throws Exception
	{
		findJavac = Outside.service(this,"gus06.java.jdk.javacfile");
		buildListing = Outside.service(this,"gus06.java.compiler.buildlisting");
		debugScripts = Outside.service(this,"gus06.java.compiler.debug.scripts");
		handleProcess = Outside.service(this,"gus06.java.compiler.v1.handleprocess");
		out = (PrintStream) Outside.resource(this,"g#gus06.java.compiler.outputanalyzer");
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("srcDir")) {srcDir = (File) obj;return;}
		if(key.equals("binDir")) {binDir = (File) obj;return;}
		if(key.equals("libDir")) {libDir = (File) obj;return;}
		if(key.equals("filter")) {filter = (F) obj;return;}
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	
	public void e() throws Exception
	{
		binDir.mkdirs();
		libDir.mkdirs();

		if(!srcDir.isDirectory()) throw new Exception("Src directory not found: "+srcDir);
		if(!binDir.isDirectory()) throw new Exception("Bin directory not found: "+binDir);
		if(!libDir.isDirectory()) throw new Exception("Lib directory not found: "+libDir);

		File javacFile = (File) findJavac.g();
		File listingFile = (File) buildListing.t(new Object[]{srcDir,filter});
		
		if(listingFile.length()==0)
		{
			out.println("No compilation required");
			return;
		}

		debugScripts.p(new File[]{srcDir,binDir,javacFile,listingFile});
        
		String cmd = d(javacFile)+" -classpath "+d(libDir+"/*")+" -sourcepath "+d(srcDir)+" -d "+d(binDir)+" @"+d(listingFile);
        
		Process pr = Runtime.getRuntime().exec(cmd,null,javacFile.getParentFile());
		handleProcess.p(pr);
	}



	private String d(File path)
	{return d(path.getAbsolutePath());}

	private String d(String value)
	{
		if(!value.contains(" ")) return value;
		return value = "\""+value+"\"";
	}
}
