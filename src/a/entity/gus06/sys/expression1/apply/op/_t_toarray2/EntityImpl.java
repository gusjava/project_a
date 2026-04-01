package a.entity.gus06.sys.expression1.apply.op._t_toarray2;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191113";}
	
	
	private Service buildMap;
	
	public EntityImpl() throws Exception
	{
		buildMap = Outside.service(this,"gus06.array.d2.objectarray.findall3.buildmap");
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
			int[] n = toInt2(obj);
			int nb1 = n[0];
			int nb2 = n[1];
			
			Object[][] data = new Object[nb1][nb2];
			for(int i=0;i<nb1;i++) for(int j=0;j<nb2;j++)
			data[i][j] = t.t(buildMap(data,i,j));
			return data;
		}
	}
	
	
	
	
	private int[] toInt2(Object obj) throws Exception
	{
		if(obj instanceof int[]) return (int[]) obj;
		if(obj instanceof String) return stringToInt2((String) obj);
		if(obj instanceof Integer) return intToInt2((Integer) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private int[] stringToInt2(String s)
	{
		if(s.contains("-"))
		{
			String[] ss = s.split("-");
			return new int[]{toInt(ss[0]),toInt(ss[1])};
		}
		int n = toInt(s);
		return new int[]{n,n};
	}
	
	private int[] intToInt2(Integer n)
	{
		return new int[]{n.intValue(),n.intValue()};
	}
	
	private int toInt(String s)
	{return Integer.parseInt(s);}
	
	
	
	private Map buildMap(Object[][] input, int i, int j) throws Exception
	{return (Map) buildMap.t(new Object[]{input,i,j});}
}
