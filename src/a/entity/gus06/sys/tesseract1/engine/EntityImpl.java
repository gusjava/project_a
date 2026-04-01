package a.entity.gus06.sys.tesseract1.engine;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20210606";}

	public static final String EXENAME = "tesseract.exe";

	private Service randomNumber;
	private Service readString;
	private Service writeString;
	private Service initRoot;
	
	private File tempDir;
	private File rootDir;
	
	
	public EntityImpl() throws Exception
	{
		randomNumber = Outside.service(this,"gus06.data.generate.string.random.number10");
		readString = Outside.service(this,"gus06.file.read.string.autodetect");
		writeString = Outside.service(this,"gus06.file.write.string");
		initRoot = Outside.service(this,"gus06.sys.tesseract1.init");
		
		tempDir = (File) Outside.resource(this,"defaultdir");
		rootDir = (File) Outside.resource(this,"path#path.tesseract1.rootdir");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		File inputFile = (File) obj;
		
		String token = (String) randomNumber.g();
		File outputFile = new File(tempDir,token+".txt");
		File batchFile = new File(tempDir,token+".bat");
		
		String outputPath = outputFile.getAbsolutePath();
		outputPath = outputPath.substring(0,outputPath.length()-4);
		
		File exeFile = new File(rootDir, EXENAME);
		if(!exeFile.exists()) initRoot.p(rootDir);
		if(!exeFile.exists()) throw new Exception("Failed to init tesseract root dir: "+rootDir);
		
		StringBuffer batch = new StringBuffer();
		
		batch.append("set TESSDATA_PREFIX=");
		batch.append(p(rootDir));
		batch.append("\n");
		
		batch.append(p(exeFile));
		batch.append(" -l fra ");
		batch.append(p(inputFile));
		batch.append(" ");
		batch.append(outputPath);
		
		writeString.p(new Object[]{batchFile,batch.toString()});
		
		String cmd = "cmd /c \""+p(batchFile)+"\"";
		Process p = Runtime.getRuntime().exec(cmd);
		int code = p.waitFor();
		if(code!=0) throw new Exception("Failed to execute command line: "+cmd);
		
		String output = (String) readString.t(outputFile);
		
		outputFile.delete();
		batchFile.delete();
		return output;
	}
	
	private String p(File f)
	{return f.getAbsolutePath();}
}