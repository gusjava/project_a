package a.entity.gus06.sys.webserver1.web2.zdyn.e.html;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140930";}


	private Service head;
	private Service body;


	public EntityImpl() throws Exception
	{
		head = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.html.head");
		body = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.html.body");
	}
	
	
	public void p(Object obj) throws Exception
	{
		P h = (P)((R)obj).r("data h");
		
		h.p("<!DOCTYPE html>");
		h.p("<html>");

		h.p("<head>");
		head.p(obj);
		h.p("</head>");

		h.p("<body>");
		body.p(obj);
		h.p("</body>");

		h.p("</html>");
	}
}
