package a.entity.gus06.file.editor.ext.eml;

import a.framework.*;
import java.io.File;
import javax.mail.Message;

public class EntityImpl implements Entity, I, P, G {

	public String creationDate() {return "20240320";}

	private Service messageViewer;
	private Service readEml;
	
	private File file;
	

	public EntityImpl() throws Exception
	{
		messageViewer = Outside.service(this,"*gus06.sys.mailclient1.tool.show.message.viewer");
		readEml = Outside.service(this,"gus06.file.read.mail");
	}
	
	
	public Object i() throws Exception
	{return messageViewer.i();}
	
	
	public Object g() throws Exception
	{return file;}
	
	
	
	public void p(Object obj) throws Exception
	{
		file = (File) obj;
		if(file==null || !file.isFile() || file.length()==0) resetGui();
		else updateGui();
	}
	
	private void resetGui() throws Exception
	{
		messageViewer.p(null);
	}
	
	private void updateGui() throws Exception
	{
		Message msg = (Message) readEml.t(file);
		messageViewer.p(msg);
	}
}