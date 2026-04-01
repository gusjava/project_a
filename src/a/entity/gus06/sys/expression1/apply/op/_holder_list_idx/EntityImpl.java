package a.entity.gus06.sys.expression1.apply.op._holder_list_idx;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191114";}


	private Service performList;
	private Service performArray;
	
	public EntityImpl() throws Exception
	{
		performList = Outside.service(this,"gus06.data.buildholder.list.index.gp");
		performArray = Outside.service(this,"gus06.data.buildholder.array.index.gp");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof List) return new T1((List) obj);
		if(obj instanceof Object[]) return new T2((Object[]) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class T1 implements T
	{
		private List list;
		public T1(List list){this.list = list;}
		
		public Object t(Object obj) throws Exception
		{
			int index = toInt(obj);
			return performList.t(new Object[]{list,index});
		}
	}
	
	private class T2 implements T
	{
		private Object[] array;
		public T2(Object[] array){this.array = array;}
		
		public Object t(Object obj) throws Exception
		{
			int index = toInt(obj);
			return performArray.t(new Object[]{array,index});
		}
	}
	
	private int toInt(Object obj)
	{return Integer.parseInt(""+obj);}
}
