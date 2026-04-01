package a.entity.gus06.sys.webserver1.web2.site.pseudo.execute.op.get;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20141017";}


	public EntityImpl() throws Exception
	{
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		R mr = (R) o[0];
		String info = (String) o[1];
	}
}
