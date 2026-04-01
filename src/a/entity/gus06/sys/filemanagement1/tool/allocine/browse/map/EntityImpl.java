package a.entity.gus06.sys.filemanagement1.tool.allocine.browse.map;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20210306";}

	public static final String KEY_LINK = "link";
	

	private Service browseLink;

	public EntityImpl() throws Exception
	{
		browseLink = Outside.service(this,"gus06.awt.desktop.browse");
	}
	
	public void p(Object obj) throws Exception
	{
		Map prop = (Map) obj;
		
		if(prop==null) return;
		if(!prop.containsKey(KEY_LINK)) return;
		
		String link = (String) prop.get(KEY_LINK);
		browseLink.p(link);
	}
}