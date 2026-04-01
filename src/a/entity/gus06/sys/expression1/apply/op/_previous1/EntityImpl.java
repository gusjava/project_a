package a.entity.gus06.sys.expression1.apply.op._previous1;

import a.framework.*;
import java.util.Iterator;
import java.io.File;
import java.util.Date;
import java.sql.ResultSet;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160306";}


	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.data.perform.iterate.previous");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof String) return new T1(obj);
		if(obj instanceof Integer) return new T1(obj);
		if(obj instanceof File) return new T1(obj);
		if(obj instanceof Date) return new T1(obj);
		
		if(obj instanceof G) return new T1(obj);
		if(obj instanceof Iterator) return new T1(obj);
		if(obj instanceof ResultSet) return new T1(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class T1 implements T
	{
		private Object data;
		public T1(Object data) {this.data = data;}
		
		public Object t(Object obj) throws Exception
		{return performManyTimes(data,(toInt(obj)));}
	}
	
	
	private Object performManyTimes(Object data, int times) throws Exception
	{
		for(int i=0;i<times;i++) data = perform.t(data);
		return data;
	}
	
	private int toInt(Object obj)
	{return Integer.parseInt(""+obj);}
}
