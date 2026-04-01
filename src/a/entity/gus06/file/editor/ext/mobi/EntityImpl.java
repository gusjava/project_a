package a.entity.gus06.file.editor.ext.mobi;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, I, P, G {

	public String creationDate() {return "20190923";}

	
	private Service tab;
	private Service extractor;
	private Service coverViewer;
	private Service dataViewer;
	
	private File file;
	


	public EntityImpl() throws Exception
	{
		tab = Outside.service(this,"*gus06.swing.tabbedpane.holder1");
		extractor = Outside.service(this,"gus06.file.mobi.extractor");
		coverViewer = Outside.service(this,"*gus06.swing.panel.screen.image");
		dataViewer = Outside.service(this,"*gus06.data.viewer.map");
		
		tab.v("Cover",coverViewer.i());
		tab.v("Data",dataViewer.i());
	}
	
	
	public Object i() throws Exception
	{return tab.i();}
	
	
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
		coverViewer.p(null);
		dataViewer.p(null);
	}
	
	
	private void updateGui() throws Exception
	{
		Map data = (Map) extractor.t(file);
		dataViewer.p(data);
		
		Object cover = ((G) data.get("coverG")).g();
		coverViewer.p(cover);
	}
}