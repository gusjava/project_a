package a.entity.gus06.sys.expression1.apply.op._class_src;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180129";}
	

	private Service findSrc;


	public EntityImpl() throws Exception
	{
		findSrc = Outside.service(this,"gus06.app.jarfile.classpath.findsrc");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof String) return findSrc.t(obj);
		if(obj instanceof Class) return findSrc.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
