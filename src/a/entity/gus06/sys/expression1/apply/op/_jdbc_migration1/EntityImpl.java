package a.entity.gus06.sys.expression1.apply.op._jdbc_migration1;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170515";}


	private Service perform;
	private Service toArray;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.sys.jdbcmigration1.engine");
		toArray = Outside.service(this,"gus06.find.objectarray");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof Object[]) return perform.t(obj);
		if(obj instanceof List) return perform.t(toArray.t(obj));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
