package a.entity.gus06.sys.webserver1.web2.zdyn.e.debug.page1;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20141001";}


	private Service printObj;
	
	public EntityImpl() throws Exception
	{printObj = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.debug.printobj");}

	
	public void p(Object obj) throws Exception
	{
		G mg = (G) obj;
		printObj.p(new Object[]{mg,mg.g()});
	}
}
