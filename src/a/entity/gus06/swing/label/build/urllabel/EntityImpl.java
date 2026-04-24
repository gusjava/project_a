package a.entity.gus06.swing.label.build.urllabel;

import a.framework.*;
import javax.swing.JLabel;
import java.net.URL;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191028";}


	private Service labelCust;
	private Service toUrl;
	
	public EntityImpl() throws Exception
	{
		labelCust = Outside.service(this,"gus06.swing.label.cust.link.web");
		toUrl = Outside.service(this,"gus.y.find1.url");
	}
	
	public Object t(Object obj) throws Exception
	{
		URL url = (URL) toUrl.t(obj);
		JLabel label = new JLabel(url.toString());
		labelCust.p(label);
		return label;
	}
}
