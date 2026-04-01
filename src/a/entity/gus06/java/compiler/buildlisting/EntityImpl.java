package a.entity.gus06.java.compiler.buildlisting;

import a.framework.*;
import java.io.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140725";}


	private Service tmpFile;

	public EntityImpl() throws Exception
	{
		tmpFile = Outside.service(this,"gus06.file.tmpfile");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof File) return build((File) obj,null);

		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		return build((File) o[0],(F) o[1]);
	}


	private File build(File dir, F filter) throws Exception
	{
		File file = (File) tmpFile.g();
		PrintStream p = new PrintStream(file);
		print(dir,filter,p);
		p.close();
		
		if(file.length()==0) throw new Exception("Empty listing for compilation");
		return file;
	}


	private void print(File dir, F filter, PrintStream p) throws Exception
	{
		File[] f = dir.listFiles();
		for(int i=0;i<f.length;i++)
		{
			if(f[i].isDirectory()) print(f[i],filter,p);
			else if(isValid(f[i],filter)) p.println(dd(f[i]));
        	}
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