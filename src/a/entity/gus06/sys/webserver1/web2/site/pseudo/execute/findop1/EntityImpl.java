package a.entity.gus06.sys.webserver1.web2.site.pseudo.execute.findop1;

import a.framework.*;

public class EntityImpl implements Entity, R {

	public String creationDate() {return "20141017";}


	private Service op_put;
	private Service op_get;


	public EntityImpl() throws Exception
	{
		op_put = Outside.service(this,"gus06.sys.webserver1.web2.site.pseudo.execute.op.put");
		op_get = Outside.service(this,"gus06.sys.webserver1.web2.site.pseudo.execute.op.get");
	}
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("put")) return op_put;
		if(key.equals("get")) return op_get;
		return null;
	}
}
