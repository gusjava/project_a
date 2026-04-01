package a.entity.gus06.sys.webserver1.web2.zdyn.e.main1;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140930";}


	private Service prepare;
	private Service action;
	private Service page;


	public EntityImpl() throws Exception
	{
		prepare = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.main1.prepare");
		action = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.main1.action");
		page = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.main1.page");
	}
	
	public void p(Object obj) throws Exception
	{
		prepare.p(obj);
		if(hasAction(obj)) action.p(obj);
		if(hasPage(obj)) page.p(obj);
	}
	
	
	private boolean hasAction(Object obj) throws Exception
	{return ((F) obj).f("data action");}
	
	private boolean hasPage(Object obj) throws Exception
	{return ((F) obj).f("data page");}
}
