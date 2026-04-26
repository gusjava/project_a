package a.entity.gus06.appli.gusexplorer.execute.tools.clipboard.snapshot;

import a.framework.*;
import java.io.File;
import java.util.List;
import java.awt.Image;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20220929";}
	

	private Service newViewerString;
	private Service newViewerImage;
	private Service newViewerFile;
	private Service newViewerList;
	
	private Service clipboard;
	private Service showInFrame;
	private Service now;

	public EntityImpl() throws Exception
	{
		newViewerString = Outside.service(this,"factory#gus06.data.editor.string.textarea.editor1");
		newViewerImage = Outside.service(this,"factory#gus06.data.viewer.image");
		newViewerFile = Outside.service(this,"factory#gus06.data.viewer.file");
		newViewerList = Outside.service(this,"factory#gus06.data.viewer.filearray");
		
		clipboard = Outside.service(this,"gus06.clipboard.access");
		showInFrame = Outside.service(this,"gus06.swing.comp.inframe.alwaysontop");
		now = Outside.service(this,"gus.x.time.now.hhmmss1");
	}
	
	public void e() throws Exception
	{
		Object content = clipboard.g();
		Object viewer = viewerFor(content);
		
		((P) viewer).p(content);
		Object comp = ((I) viewer).i();
		
		String title = "UTIL_clipboard#Clipboard at "+now.g();
		showInFrame.v(title,comp);
	}
	
	
	private Object viewerFor(Object content) throws Exception
	{
		if(content==null) return null;
		
		if(content instanceof String) return newViewerString.g();
		if(content instanceof Image) return newViewerImage.g();
		if(content instanceof File) return newViewerFile.g();
		if(content instanceof List) return newViewerList.g();
		
		throw new Exception("Invalid clipboard content type: "+content.getClass().getName());
	}
}
