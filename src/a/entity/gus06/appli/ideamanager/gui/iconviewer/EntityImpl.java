package a.entity.gus06.appli.ideamanager.gui.iconviewer;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.util.Map;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20150425";}


	private Service iconMapViewer;
	private Service mapProvider;


	private JPanel panel;

	public EntityImpl() throws Exception
	{
		iconMapViewer = Outside.service(this,"*gus06.data.viewer.map.iconmap");
		mapProvider = Outside.service(this,"gus06.app.jarfile.listing.resources.iconmap.gyem");
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) iconMapViewer.i(),BorderLayout.CENTER);
		
		Map map = (Map) mapProvider.g();
		iconMapViewer.p(map);
	}
	
	
	public Object i() throws Exception
	{return panel;}
}
