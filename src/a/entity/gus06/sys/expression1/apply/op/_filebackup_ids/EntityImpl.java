package a.entity.gus06.sys.expression1.apply.op._filebackup_ids;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161018";}

	public static final String T = "constant";


	private Service manager;
	
	public EntityImpl() throws Exception
	{
		manager = Outside.service(this,"gus06.sys.filebackup1.manager");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		return manager.g();
	}
}
