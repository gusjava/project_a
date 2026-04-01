package a.entity.gus06.sys.expression1.apply.op._g_toarray2;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191113";}
	
	

	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj instanceof G) return new T1((G) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private class T1 implements T
	{
		private G g;
		public T1(G g) {this.g = g;}
		
		public Object t(Object obj) throws Exception
		{
			int[] n = toInt2(obj);
			int n1 = n[0];
			int n2 = n[1];
			
			Object[][] data = new Object[n1][n2];
			for(int i=0;i<n1;i++) for(int j=0;j<n2;j++) data[i][j] = g.g();
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
}
