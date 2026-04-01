package a.entity.gus06.sys.git1.tool.src.normalizer;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201201";}


	private Service handleXhtml;
	private Service handleJava;
	private Service handleJs;
	
	public EntityImpl() throws Exception
	{
		handleXhtml = Outside.service(this,"gus06.sys.git1.tool.src.normalizer.xhtml");
		handleJava = Outside.service(this,"gus06.sys.git1.tool.src.normalizer.java");
		handleJs = Outside.service(this,"gus06.sys.git1.tool.src.normalizer.js");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String src = (String) o[0];
		File file = (File) o[1];
		
		return normalize(src,file);
	}
	
	
	private String normalize(String src, File file) throws Exception
	{
		try
		{
			String name = file.getName().toLowerCase();
		
			if(name.endsWith(".xhtml")) return (String) handleXhtml.t(src);
			if(name.endsWith(".java")) return (String) handleJava.t(src);
			if(name.endsWith(".js")) return (String) handleJs.t(src);
			return src;
		}
		catch(Exception e)
		{
			String message = "Failed to normalize src for file: "+file;
			throw new Exception(message,e);
		}
	}
}