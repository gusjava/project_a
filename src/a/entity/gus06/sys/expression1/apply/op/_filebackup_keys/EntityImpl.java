package a.entity.gus06.sys.expression1.apply.op._filebackup_keys;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161018";}


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
		
		if(obj==null) return null;
		
		if(obj instanceof String) return keys((String) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private Object keys(String id) throws Exception
	{
		G g = (G) manager.r(id);
		return g.g();
	}
}
