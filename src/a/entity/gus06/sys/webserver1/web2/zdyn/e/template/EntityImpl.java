package a.entity.gus06.sys.webserver1.web2.zdyn.e.template;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20141001";}


	private Service findPage;
	private Service perform1;
	
	public EntityImpl() throws Exception
	{
		findPage = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.find.page");
		perform1 = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.template.perform1");
	}
	
	
	public void p(Object obj) throws Exception
	{
		String id = (String) findPage.t(obj);
		perform1.p(new Object[]{obj,id});
	}
}
