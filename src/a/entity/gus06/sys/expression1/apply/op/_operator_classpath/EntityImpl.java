package a.entity.gus06.sys.expression1.apply.op._operator_classpath;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180203";}
	

	private Service findDoc;

	public EntityImpl() throws Exception
	{
		findDoc = Outside.service(this,"gus06.sys.expression1.apply.opdata.classpath");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof String) return findDoc.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
