package a.entity.gus06.java.compiler.debug.scripts;

import a.framework.*;
import java.io.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140725";}

	public static final String FILENAME_CMD = "compile.bat";
	public static final String FILENAME_CMD_V = "compile_verbose.bat";
	public static final String FILENAME_CMD_XL = "compile_xlint.bat";
	public static final String FILENAME_CMD_XD = "compile_xdiags.bat";


	private File tmpDir;

	public EntityImpl() throws Exception
	{
		tmpDir = (File) Outside.resource(this,"defaultdir");
	}
	
	
	public void p(Object obj) throws Exception
	{
		File[] f = (File[]) obj;
		if(f.length!=4) throw new Exception("Wrong data number: "+f.length);

		File src = f[0];
		File bin = f[1];
		File javac = f[2];
		File listing = f[3];
		
		tmpDir.mkdirs();
		if(!tmpDir.isDirectory()) throw new Exception("Tmp directory not found: "+tmpDir);

		String cmd = cmd(src,bin,javac,listing);
		String cmd_v = cmd(src,bin,javac,listing,"-verbose");
		String cmd_xl = cmd(src,bin,javac,listing,"-Xlint:unchecked");
		String cmd_xd = cmd(src,bin,javac,listing,"-Xdiags:verbose");

		build(FILENAME_CMD,cmd);
		build(FILENAME_CMD_V,cmd_v);
		build(FILENAME_CMD_XL,cmd_xl);
		build(FILENAME_CMD_XD,cmd_xd);
	}




	private void build(String fileName, String cmd) throws Exception
	{
		File file = new File(tmpDir,fileName);
		PrintStream p = new PrintStream(file);
		p.println(cmd);
		p.println("pause");
		p.close();
	}


	private String cmd(File src, File bin, File javac, File listing)
	{return d(javac)+" -sourcepath "+d(src)+" -d "+d(bin)+" @"+d(listing);}


	private String cmd(File src, File bin, File javac, File listing, String option)
	{return d(javac)+" "+option+" -sourcepath "+d(src)+" -d "+d(bin)+" @"+d(listing);}


	private String d(File path)
	{return d(path.getAbsolutePath());}
    

	private String d(String value)
	{
		if(!value.contains(" ")) return value;
		return value = "\""+value+"\"";
	}

}
