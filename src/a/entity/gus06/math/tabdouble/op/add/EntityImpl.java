package a.entity.gus06.math.tabdouble.op.add;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180503";}


	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof List) return handleList((List) obj);
		if(obj instanceof Object[]) return handleArray((Object[]) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private double[] handleList(List list) throws Exception
	{
		if(list.isEmpty()) return null;
		double[] d0 = (double[]) list.get(0);
		int dim = d0.length;
		
		double[] r = new double[dim];
		for(int i=0;i<dim;i++) r[i] = d0[i];
		
		for(int i=1;i<list.size();i++)
		{
			double[] d = (double[]) list.get(i);
			if(d.length!=dim) throw new Exception("Invalid array length: "+d.length);
			
			for(int j=0;j<dim;j++) r[j] = r[j] + d[j];
		}
		return r;
	}
	
	
	private double[] handleArray(Object[] array) throws Exception
	{
		if(array.length==0) return null;
		double[] d0 = (double[]) array[0];
		int dim = d0.length;
		
		double[] r = new double[dim];
		for(int i=0;i<dim;i++) r[i] = d0[i];
		
		for(int i=1;i<array.length;i++)
		{
			double[] d = (double[]) array[i];
			if(d.length!=dim) throw new Exception("Invalid array length: "+d.length);
			
			for(int j=0;j<dim;j++) r[j] = r[j] + d[j];
		}
		return r;
	}
}
