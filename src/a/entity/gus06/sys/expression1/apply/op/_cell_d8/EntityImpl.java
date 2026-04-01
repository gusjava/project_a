package a.entity.gus06.sys.expression1.apply.op._cell_d8;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180328";}


	private Service perform;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.data.perform.cell.d8");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof Object[][])		return new T1(obj);
		if(obj instanceof double[][])		return new T1(obj);
		if(obj instanceof int[][])		return new T1(obj);
		if(obj instanceof long[][])		return new T1(obj);
		if(obj instanceof float[][])		return new T1(obj);
		if(obj instanceof boolean[][])		return new T1(obj);
		if(obj instanceof char[][])		return new T1(obj);
		if(obj instanceof byte[][])		return new T1(obj);
		if(obj instanceof short[][])		return new T1(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class T1 implements T
	{
		private Object data;
		public T1(Object data) {this.data = data;}
		
		public Object t(Object obj) throws Exception
		{
			if(obj instanceof int[])
			{
				int[] n = (int[]) obj;
				if(n.length!=2) throw new Exception("Invalid array length: "+n.length);
				
				Integer x = Integer.valueOf(n[0]);
				Integer y = Integer.valueOf(n[1]);
				return perform.t(new Object[]{data,x,y});
			}
			if(obj instanceof Object[])
			{
				Object[] n = (Object[]) obj;
				if(n.length!=2) throw new Exception("Invalid array length: "+n.length);
				
				return perform.t(new Object[]{data,n[0],n[1]});
			}
			if(obj instanceof List)
			{
				List n = (List) obj;
				if(n.size()!=2) throw new Exception("Invalid list size: "+n.size());
				
				return perform.t(new Object[]{data,n.get(0),n.get(1)});
			}
			if(obj instanceof Integer)
			{
				Integer n = (Integer) obj;
				return new T2(data,n);
			}
			if(obj instanceof String)
			{
				String n = (String) obj;
				return new T2(data,n);
			}
			throw new Exception("Invalid data type: "+obj.getClass().getName());
		}
	}
	
	
	
	private class T2 implements T
	{
		private Object data;
		private Object xRule;
		
		public T2(Object data, Object xRule)
		{
			this.data = data;
			this.xRule = xRule;
		}
		
		public Object t(Object obj) throws Exception
		{
			if(obj instanceof Integer)
			{
				Integer yRule = (Integer) obj;
				return perform.t(new Object[]{data,xRule,yRule});
			}
			if(obj instanceof String)
			{
				String yRule = (String) obj;
				return perform.t(new Object[]{data,xRule,yRule});
			}
			throw new Exception("Invalid data type: "+obj.getClass().getName());
		}
	}
}
