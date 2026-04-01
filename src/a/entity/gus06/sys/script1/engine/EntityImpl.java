package a.entity.gus06.sys.script1.engine;

import a.framework.*;
import java.io.File;
import java.io.PrintStream;
import java.util.Map;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20151111";}


	private Service engineFromText;
	private Service engineFromFile;
	private Service buildFile;
	private Service getInside;
	private Service manager;

	public EntityImpl() throws Exception
	{
		engineFromText = Outside.service(this,"gus06.sys.script1.engine.fromtext");
		engineFromFile = Outside.service(this,"gus06.sys.script1.engine.fromfile");
		buildFile = Outside.service(this,"gus06.sys.script1.tool.build.file");
		getInside = Outside.service(this,"gus06.app.inside.script2");
		manager = Outside.service(this,"gus06.sys.script1.manager");
	}
	
	
	public void p(Object obj) throws Exception
	{t(obj);}
	
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object src = o[0];
		Map context = (Map) o[1];
		
		return handle(src,context);
	}
	
	
	
	private Object handle(Object src, Map context) throws Exception
	{
		if(src==null) throw new Exception("Invalid source object: null");
		
		if(src instanceof G)		return handleG((G)src,context);
		if(src instanceof File)		return handleFile((File)src,context);
		if(src instanceof String)	return handleString((String)src,context);
		
		throw new Exception("Invalid source type: "+src.getClass().getName());
	}
	
	
	private Object handleG(G g, Map context) throws Exception
	{
		return handle(g.g(),context);
	}
	
	private Object handleFile(File src, Map context) throws Exception
	{
		return engineFromFile(src,context);
	}
	
	private Object handleString(String src, Map context) throws Exception
	{
		File f = null;
		try{f = (File) buildFile.t(new Object[]{src,context});}
		catch(Exception e){}
		
		if(f!=null && f.isFile()) return handleFile(f,context);
		
		String scriptInside = null;
		try{scriptInside = (String) getInside.t(src);}
		catch(Exception e){}
		
		if(scriptInside!=null) return engineFromText(scriptInside,context);
		
		return engineFromText(src,context);
	}
	
	
	
	
	
	
	
	private Object engineFromFile(File src, Map context) throws Exception
	{
		E watcher = (E) manager.t(new Object[]{src,context});
		Object result = engineFromFile.t(new Object[]{src,context});
		watcher.e();
		return result;
	}
	
	private Object engineFromText(String src, Map context) throws Exception
	{
		E watcher = (E) manager.t(new Object[]{src,context});
		Object result = engineFromText.t(new Object[]{src,context});
		watcher.e();
		return result;
	}
}
