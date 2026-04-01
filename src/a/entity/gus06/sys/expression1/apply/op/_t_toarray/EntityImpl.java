package a.entity.gus06.sys.expression1.apply.op._t_toarray;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191113";}
	
	
	private Service buildMap;
	
	public EntityImpl() throws Exception
	{
		buildMap = Outside.service(this,"gus06.array.objectarray.findall3.buildmap");
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
			Object[] array = new Object[nb];
			for(int i=0;i<nb;i++) 
			array[i] = t.t(buildMap(array,i));
			return array;
		}
	}
	
	private int toInt(Object obj)
	{return Integer.parseInt(""+obj);}
	
	
	
	private Map buildMap(Object[] input, int i) throws Exception
	{return (Map) buildMap.t(new Object[]{input,i});}
}
