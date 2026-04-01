package a.entity.gus06.java.compiler.v3.buildlisting;

import a.framework.*;
import java.io.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200229";}


	private Service tmpFile;

	public EntityImpl() throws Exception
	{
		tmpFile = Outside.service(this,"gus06.file.tmpfile");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File srcDir = (File) o[0];
		File binDir = (File) o[1];
		F filter = (F) o[2];
		
		File listingFile = (File) tmpFile.g();
		PrintStream p = new PrintStream(listingFile);
		int len = srcDir.getAbsolutePath().length();
		printDir(len,binDir,filter,p,srcDir);
		p.close();
		
		if(listingFile.length()==0) throw new Exception("Empty listing for compilation");
		return listingFile;
	}


	private void printDir(int len, File binDir, F filter, PrintStream p, File dir) throws Exception
	{
		File[] f = dir.listFiles();
		for(int i=0;i<f.length;i++)
		{
			if(f[i].isDirectory()) printDir(len,binDir,filter,p,f[i]);
			else printFile(len,binDir,filter,p,f[i]);
        	}
	}
	
	
	
	private void printFile(int len, File binDir, F filter, PrintStream p, File javaFile) throws Exception
	{
		if(!isValid(javaFile,filter)) return;
		
		String path = javaFile.getAbsolutePath();
		if(!path.endsWith(".java")) return;
		
		String relPath = path.substring(len,path.length()-5);
		if(relPath.startsWith(File.separator)) relPath = relPath.substring(1);
		
		File classFile = new File(binDir,relPath+".class");
		if(isUnchanged(javaFile,classFile)) return;
		
		p.println(dd(javaFile));
	}
	
	
	private boolean isUnchanged(File javaFile, File classFile)
	{
		if(!classFile.exists()) return false;
		
		long javaTime = javaFile.lastModified();
		long classTime = classFile.lastModified();
		
		return classTime>javaTime;
	}




	private boolean isValid(File file, F filter) throws Exception
	{
		try
		{
			if(!file.getName().endsWith(".java")) return false;
			return filter==null?true:filter.f(file);
		}
		catch(Exception e)
		{
			String message = "Failed to check validity for file: "+file;
			throw new Exception(message,e);
		}
	}



	private String dd(File path)
	{return dd(path.getAbsolutePath());}


	private String dd(String value)
	{
		if(!value.contains(" ")) return value;
		value = value.replace(File.separator,"\\"+File.separator);
		value = value.replace(":","\\:");
		return value = "\""+value+"\"";
	}
}