package a.entity.gus06.sys.expression1.apply.op._distance_euclidean;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160711";}


	private Service performDouble;
	private Service performInt;

	public EntityImpl() throws Exception
	{
		performDouble = Outside.service(this,"gus06.math.tabdouble.distance.euclidean");
		performInt = Outside.service(this,"gus06.math.tabint.distance.euclidean");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof double[]) return new T1((double[]) obj);
		if(obj instanceof int[]) return new T2((int[]) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class T1 implements T
	{
		private double[] d1;
		public T1(double[] d1) {this.d1 = d1;}
		
		public Object t(Object obj) throws Exception
		{
			double[] d2 = (double[]) obj;
			return performDouble.t(new Object[]{d1,d2});
		}
	}
	
	private class T2 implements T
	{
		private int[] d1;
		public T2(int[] d1) {this.d1 = d1;}
		
		public Object t(Object obj) throws Exception
		{
			int[] d2 = (int[]) obj;
			return performInt.t(new Object[]{d1,d2});
		}
	}
}
