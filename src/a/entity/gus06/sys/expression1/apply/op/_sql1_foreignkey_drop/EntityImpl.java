package a.entity.gus06.sys.expression1.apply.op._sql1_foreignkey_drop;

import a.framework.*;
import java.util.Map;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201230";}


	private Service perform;
	private Service findArray;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.jdbc.mysql.sql.foreignkey.drop");
		findArray = Outside.service(this,"gus06.find.objectarray");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof String) return new T1((String) obj);
		if(obj instanceof List) return perform.t(findArray.t(obj));
		if(obj instanceof Object[]) return perform.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private class T1 implements T
	{
		private String path;
		
		public T1(String path)
		{this.path = path;}
		
		public Object t(Object obj) throws Exception
		{return perform.t(new Object[]{path,(String) obj});}
	}
}