package a.entity.gus06.file.editor.ext.zip.entryviewer.panel.java;

import a.framework.*;
import java.io.InputStream;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20140809";}


	private Service isToString;
	private Service srcViewer;


	public EntityImpl() throws Exception
	{
		isToString = Outside.service(this,"gus06.io.transfer.tostring.autodetect");
		srcViewer = Outside.service(this,"*gus06.data.viewer.string.src.java");
	}
	
	
	public Object i() throws Exception
	{return srcViewer.i();}
	
	
	
	public void p(Object obj) throws Exception
	{
		InputStream is = (InputStream) ((G) obj).g();
		String src = (String) isToString.t(is);
		srcViewer.p(src);
	}
}