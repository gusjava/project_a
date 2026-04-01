package a.entity.gus06.sys.expression1.apply.op._t_tolist;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191113";}
	
	
	private Service buildMap;
	
	public EntityImpl() throws Exception
	{
		buildMap = Outside.service(this,"gus06.list.findall3.buildmap");
	}
	
	

	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj instanceof T) return new T1((T) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private class T1 implements T
	{
		private T t;
		public T1(T t) {this.t = t;}
		
		public Object t(Object obj) throws Exception
		{
			int nb = toInt(obj);
			List list = new ArrayList();
			for(int i=0;i<nb;i++) 
			list.add(t.t(buildMap(list,i)));
			return list;
		}
	}
	
	private int toInt(Object obj)
	{return Integer.parseInt(""+obj);}
	
	
	
	private Map buildMap(List input, int i) throws Exception
	{return (Map) buildMap.t(new Object[]{input,i});}
}
