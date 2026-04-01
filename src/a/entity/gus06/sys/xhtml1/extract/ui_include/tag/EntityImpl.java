package a.entity.gus06.sys.xhtml1.extract.ui_include.tag;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220907";}


	private Service readFile;
	private Service extractAll;

	public EntityImpl() throws Exception
	{
		readFile = Outside.service(this,"gus06.file.read.string.autodetect");
		extractAll = Outside.service(this,"gus06.string.extract.xhtml.tag.type.ui_include.a");
	}
	
	public Object t(Object obj) throws Exception
	{
		String text = toText(obj);
		return extractAll.t(text);
	}
	
	private String toText(Object obj) throws Exception
	{
		if(obj instanceof String) return (String) obj;
		if(obj instanceof File) return (String) readFile.t(obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
