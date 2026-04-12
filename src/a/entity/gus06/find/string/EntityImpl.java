package a.entity.gus06.find.string;

import a.framework.*;
import java.io.InputStream;
import java.io.File;
import java.nio.file.Path;
import java.net.URL;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250715";}


	private Service fromFile;
	private Service fromPath;
	private Service fromURL;
	private Service fromIS;
	
	public EntityImpl() throws Exception
	{
		fromFile = Outside.service(this,"gus06.file.read.string.autodetect");
		fromPath = Outside.service(this,"gus06.file.read.string.autodetect");
		fromURL = Outside.service(this,"gus06.convert.urltostring");
		fromIS = Outside.service(this,"gus06.io.transfer.tostring");
	}
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		if(obj instanceof String) return obj;
		if(obj instanceof File) return fromFile.t(obj);
		if(obj instanceof Path) return fromFile.t(pathToFile((Path) obj));
		if(obj instanceof URL) return fromURL.t(obj);
		if(obj instanceof InputStream) return fromIS.t(obj);
		if(obj instanceof StringBuffer) return obj.toString();
		if(obj instanceof StringBuilder) return obj.toString();
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private File pathToFile(Path path)
	{return path.toFile();}
}
