package a.entity.gus06.file.editor.ext.ods;

import a.framework.*;
import java.io.File;
import org.jopendocument.model.OpenDocument;
import org.jopendocument.panel.ODSViewerPanel;

public class EntityImpl implements Entity, I, P, G {

	public String creationDate() {return "20170304";}


	private Service shiftPanel;

	private File file;
	
	public EntityImpl() throws Exception
	{
		shiftPanel = Outside.service(this,"gus.x.swing.panel.shiftpanel");
	}
	
	
	public Object i() throws Exception
	{return shiftPanel.i();}
	
	
	public Object g() throws Exception
	{return file;}
	
	
	
	public void p(Object obj) throws Exception
	{
		file = (File) obj;
		if(file==null || !file.isFile() || file.length()==0) resetGui();
		else updateGui();
	}
	
	
	
	
	private void updateGui() throws Exception
	{
		OpenDocument doc = new OpenDocument(file);
		ODSViewerPanel viewer = new ODSViewerPanel(doc);
		shiftPanel.p(viewer);
	}
	
	
	
	private void resetGui() throws Exception
	{shiftPanel.p(null);}
}