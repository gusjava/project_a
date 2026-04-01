package a.entity.gus06.appli.vindinium.data.viewurl.browse;

import java.awt.Desktop;
import java.net.URL;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20170923";}

	public void p(Object obj) throws Exception
	{
		Map data = (Map) obj;
		String viewUrl = (String) data.get(DATA.K_VIEWURL);
		Desktop.getDesktop().browse(new URL(viewUrl).toURI());
	}
}
