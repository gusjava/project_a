package a.entity.gus06.math.vectors.sum.doublearray;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151205";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] oo = (Object[]) obj;
		int nb = oo.length;
		
		if(nb==0) return null;
		
		int dim = dim(oo[0]);
		
		double[] r = new double[dim];
		for(int i=0;i<dim;i++) r[i] = 0;
		
		for(int i=0;i<nb;i++) add(r,oo[i]);
		return r;
	}
	
	
	private int dim(Object obj) throws Exception
	{
		if(obj instanceof int[]) return ((int[]) obj).length;
		if(obj instanceof double[]) return ((double[]) obj).length;
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private void add(double[] r, Object obj) throws Exception
	{
		if(obj instanceof int[]) {add(r,(int[]) obj);return;}
		if(obj instanceof double[]) {add(r,(double[]) obj);return;}
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private void add(double[] r, int[] v) throws Exception
	{
		if(r.length!=v.length) throw new Exception("Unable to sum vectors with different dimensions");
		for(int i=0;i<r.length;i++) r[i] += v[i];
	}
	
	private void add(double[] r, double[] v) throws Exception
	{
		if(r.length!=v.length) throw new Exception("Unable to sum vectors with different dimensions");
		for(int i=0;i<r.length;i++) r[i] += v[i];
	}
}
