package a.entity.gus06.sys.expression1.apply.op._app_path1;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170408";}


	private Service fp;
		
	public EntityImpl() throws Exception
	{
		fp = Outside.service(this,"fileprovider");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof String)	return fp.r((String) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
