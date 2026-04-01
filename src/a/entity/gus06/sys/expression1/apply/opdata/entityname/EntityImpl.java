package a.entity.gus06.sys.expression1.apply.opdata.entityname;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180203";}


	private Service findDoc;

	public EntityImpl() throws Exception
	{
		findDoc = Outside.service(this,"gus06.app.jarfile.entity.finddoc");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		String op = (String) obj;
		return "gus.sys.expression1.apply.op."+op;
	}
}