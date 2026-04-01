package a.entity.gus06.data.viewer.string.json;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JTabbedPane;
import java.util.Map;

public class EntityImpl implements Entity, I, P, G {

	public String creationDate() {return "20150509";}


	private Service viewerString;
	private Service viewerMap;
	private Service jsonParser;

	private JTabbedPane tab;
	
	private String data;
	
	

	public EntityImpl() throws Exception
	{
		viewerString = Outside.service(this,"*gus06.data.viewer.string");
		viewerMap = Outside.service(this,"*gus06.data.viewer.map");
		jsonParser = Outside.service(this,"gus06.file.convert.json.parser");
		
		tab = new JTabbedPane();
		tab.addTab("Map",(JComponent) viewerMap.i());
		tab.addTab("Json",(JComponent) viewerString.i());
	}
	
	
	public Object g() throws Exception
	{return data;}
	
	
	public Object i() throws Exception
	{return tab;}
	
	
	public void p(Object obj) throws Exception
	{
		data = (String) obj;
		
		viewerString.p(data);
		viewerMap.p(parseData());
	}
	
	
	private Map parseData() throws Exception
	{
		if(data==null) return null;
		return (Map) jsonParser.t(data);
	}
}
