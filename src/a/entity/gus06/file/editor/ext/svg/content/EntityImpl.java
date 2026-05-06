package a.entity.gus06.file.editor.ext.svg.content;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20250429";}

	private Service viewer;
	private Service read;
	
	public EntityImpl() throws Exception
	{
		viewer = Outside.service(this,"*gus06.data.viewer.string.textarea.editor1");
		read = Outside.service(this,"gus.x.file.string.read.v1");
	}
	
	public Object i() throws Exception
	{return viewer.i();}
	
	
	public void p(Object obj) throws Exception
	{
		File file = (File) obj;
		String content = fileToContent(file);
		viewer.p(content);
	}
	
	private String fileToContent(File file) throws Exception
	{
		if(file==null || !file.isFile() || file.length()==0) return null;
		return (String) read.t(file);
	}
}
