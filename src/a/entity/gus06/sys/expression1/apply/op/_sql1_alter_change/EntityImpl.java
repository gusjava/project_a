package a.entity.gus06.sys.expression1.apply.op._sql1_alter_change;

import a.framework.*;
import java.util.Map;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190323";}


	private Service perform;
	private Service findArray;
	private Service convertType;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.jdbc.mysql.sql.table.alter.change");
		findArray = Outside.service(this,"gus06.find.stringarray");
		convertType = Outside.service(this,"gus06.string.transform.convert.javatype.sql");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof String) return new T1((String) obj);
		if(obj instanceof List) return perform.t(findArray.t(obj));
		if(obj instanceof String[]) return perform.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private class T1 implements T
	{
		private String path;
		
		public T1(String path)
		{this.path = path;}
		
		public Object t(Object obj) throws Exception
		{return new T2(path,(String) obj);}
	}
	
	private class T2 implements T
	{
		private String path;
		private String fieldName;
		
		public T2(String path, String fieldName)
		{
			this.path = path;
			this.fieldName = fieldName;
		}
		
		public Object t(Object obj) throws Exception
		{return new T3(path,fieldName,(String) obj);}
	}
	
	private class T3 implements T
	{
		private String path;
		private String fieldName;
		private String newName;
		
		public T3(String path, String fieldName, String newName)
		{
			this.path = path;
			this.fieldName = fieldName;
			this.newName = newName;
		}
		
		public Object t(Object obj) throws Exception
		{
			String fieldType = (String) convertType.t(obj);
			return perform.t(new String[]{path,fieldName,newName,fieldType});
		}
	}
}