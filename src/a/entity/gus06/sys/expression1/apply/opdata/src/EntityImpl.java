package a.entity.gus06.sys.expression1.apply.opdata.src;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160814";}


	private Service findSrc;

	public EntityImpl() throws Exception
	{
		findSrc = Outside.service(this,"gus06.app.jarfile.entity.findsrc");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		String op = (String) obj;
		String entityName = "gus.sys.expression1.apply.op."+op;
		return findSrc.t(entityName);
	}
}