package a.entity.gus06.sys.expression1.apply.op._completeall;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180302";}


	private Service builderT;
	private Service perform;
	
	public EntityImpl() throws Exception
	{
		builderT = Outside.service(this,"gus06.sys.expression1.builder2.t");
		perform = Outside.service(this,"gus06.data.perform.completeall");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		Map opMap = (Map) o[1];
		
		if(value==null) return null;
		
		if(value instanceof Map) return new T1(value,opMap);
		
		throw new Exception("Invalid data type: "+value.getClass().getName());
	}
	
	
	private List toList(List list, Map opMap) throws Exception
	{
		List list1 = new ArrayList();
		for(int i=0;i<list.size();i++)
		{
			Object element = list.get(i);
			Object value = i%2==0 ? element : buildT(element,opMap);
			list1.add(value);
		}
		return list1;
	}
	
	
	private T buildT(Object obj, Map opMap) throws Exception
	{return (T) builderT.t(new Object[]{obj,opMap});}
	
	
	
	private class T1 implements T
	{
		private Object data;
		private Map opMap;
		
		public T1(Object data, Map opMap)
		{
			this.data = data;
			this.opMap = opMap;
		}
		
		public Object t(Object obj) throws Exception
		{
			List list = toList((List) obj,opMap);
			return perform.t(new Object[]{data,list});
		}
	}
}
