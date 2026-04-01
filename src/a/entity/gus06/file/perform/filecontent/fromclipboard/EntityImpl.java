package a.entity.gus06.file.perform.filecontent.fromclipboard;

import a.framework.*;
import java.io.File;
import java.util.List;

public class EntityImpl implements Entity, P, F, T {

	public String creationDate() {return "20150530";}

	private Service fromClipboard;
	private Service writeToFile;
	private Service adaptJavaSrc;

	public EntityImpl() throws Exception
	{
		fromClipboard = Outside.service(this,"gus06.clipboard.access");
		writeToFile = Outside.service(this,"gus06.file.write.generic");
		adaptJavaSrc = Outside.service(this,"gus06.java.srccode.adapttofile.changesrc");
	}
	
	public void p(Object obj) throws Exception
	{t(obj);}
	
	public boolean f(Object obj) throws Exception
	{return t(obj)==null;}
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		
		Object data = fromClipboard.g();
		if(data==null) return null;
		
		if(data instanceof List) data = extractFile((List) data);
		if(data==null) return null;
		
		if(file.getName().endsWith(".java")) adaptJavaSrc.p(new Object[]{file,data});
		else writeToFile.p(new Object[]{file,data});
		
		return data;
	}
	
	private File extractFile(List list)
	{
		if(list.size()!=1) return null;
		return (File) list.get(0);
	}
}
