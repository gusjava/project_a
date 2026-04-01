package a.entity.gus06.appli.gusclient1.gui.appdoc.resources.viewer.icons;

import a.framework.*;
import javax.swing.JComponent;
import java.util.Map;


public class EntityImpl implements Entity, I {

	public String creationDate() {return "20140910";}

	
	private Service findIconMap;
	private Service mapViewer;
	
	private Map map;
	
	
	
	public EntityImpl() throws Exception
	{
		findIconMap = Outside.service(this,"gus06.app.jarfile.listing.resources.iconmap.gyem");
		mapViewer = Outside.service(this,"*gus06.data.viewer.map.iconmap");
		
		map = (Map) findIconMap.g();
		mapViewer.p(map);
	}
	
	
	
	public Object i() throws Exception
	{return mapViewer.i();}
}
