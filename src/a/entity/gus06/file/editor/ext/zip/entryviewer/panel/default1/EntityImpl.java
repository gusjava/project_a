package a.entity.gus06.file.editor.ext.zip.entryviewer.panel.default1;

import a.framework.*;
import java.io.InputStream;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20140809";}


	private Service isToString;
	private Service stringViewer;
	
	public EntityImpl() throws Exception
	{
		isToString = Outside.service(this,"gus06.io.transfer.tostring.autodetect");
		stringViewer = Outside.service(this,"*gus06.data.viewer.string");
	}
	
	
	public Object i() throws Exception
	{return stringViewer.i();}
	
	
	public void p(Object obj) throws Exception
	{
		InputStream is = (InputStream) ((G) obj).g();
		String text = (String) isToString.t(is);
		stringViewer.p(text);
	}
}