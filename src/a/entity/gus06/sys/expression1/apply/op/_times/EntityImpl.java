package a.entity.gus06.sys.expression1.apply.op._times;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151110";}
	
	
	private Service performLoop;
	
	public EntityImpl() throws Exception
	{
		performLoop = Outside.service(this,"gus06.data.perform.loop");
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof Integer)
			return new T_times_Int(toInt(obj));
		if(obj instanceof Double)
			return new T_times_Double(toDouble(obj));
		if(obj instanceof Float)
			return new T_times_Float(toFloat(obj));
		if(obj instanceof Long)
			return new T_times_Long(toLong(obj));
			
		if(obj instanceof String)
			return new T_times_String((String) obj);
		if(obj instanceof List)
			return new T_times_List((List) obj);
			
		if(obj instanceof E) return new T_loop(obj);
		if(obj instanceof G) return new T_loop(obj);
		if(obj instanceof H) return new T_loop(obj);
		if(obj instanceof P) return new T_loop(obj);
		if(obj instanceof T) return new T_loop(obj);
			
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private int toInt(Object obj)
	{return Integer.parseInt(""+obj);}
	
	private double toDouble(Object obj)
	{return Double.parseDouble(""+obj);}
	
	private float toFloat(Object obj)
	{return Float.parseFloat(""+obj);}
	
	private long toLong(Object obj)
	{return Long.parseLong(""+obj);}
	
	
	
	
	private class T_times_Int implements T
	{
		private int value;
		public T_times_Int(int value) {this.value = value;}
		
		public Object t(Object obj) throws Exception
		{return Integer.valueOf(value * toInt(obj));}
	}
	
	private class T_times_Double implements T
	{
		private double value;
		public T_times_Double(double value) {this.value = value;}
		
		public Object t(Object obj) throws Exception
		{return Double.valueOf(value * toInt(obj));}
	}
	
	private class T_times_Float implements T
	{
		private float value;
		public T_times_Float(float value) {this.value = value;}
		
		public Object t(Object obj) throws Exception
		{return Float.valueOf(value * toInt(obj));}
	}
	
	private class T_times_Long implements T
	{
		private long value;
		public T_times_Long(long value) {this.value = value;}
		
		public Object t(Object obj) throws Exception
		{return Long.valueOf(value * toInt(obj));}
	}
	
	
	
	
	
	
	private class T_times_String implements T
	{
		private String value;
		public T_times_String(String value) {this.value = value;}
		
		public Object t(Object obj) throws Exception
		{
			int a = toInt(obj);
			StringBuffer b = new StringBuffer();
			for(int i=0;i<a;i++) b.append(value);
			return b.toString();
		}
	}
	
	private class T_times_List implements T
	{
		private List value;
		public T_times_List(List value) {this.value = value;}
		
		public Object t(Object obj) throws Exception
		{
			int a = toInt(obj);
			List l = new ArrayList();
			for(int i=0;i<a;i++) l.addAll(value);
			return l;
		}
	}
	
	private class T_loop implements T
	{
		private Object value;
		public T_loop(Object value) {this.value = value;}
		
		public Object t(Object obj) throws Exception
		{return performLoop.t(new Object[]{value,obj});}
	}
}
