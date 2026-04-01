package a.entity.gus06.math.function.solve.polynomial;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160722";}


	private Service findDoubleArray;
	private Service polynomial1;
	private Service polynomial2;


	public EntityImpl() throws Exception
	{
		findDoubleArray = Outside.service(this,"gus06.find.doublearray");
		polynomial1 = Outside.service(this,"gus06.math.function.solve.polynomial1");
		polynomial2 = Outside.service(this,"gus06.math.function.solve.polynomial2");
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		double[] d = (double[]) findDoubleArray.t(obj);
		if(d.length==0) throw new Exception("Invalid polynomial coeff number: 0");
		
		d = reduce(d);
		if(d.length==0) throw new Exception("Invalid polynomial coeff number: 0");
		
		if(d.length==1) return new double[]{};
		if(d.length==2) return polynomial1.t(d);
		if(d.length==3) return polynomial2.t(d);
		
		return null;
	}
	
	
	
	
	private double[] reduce(double[] d)
	{
		int r = d.length;
		while(d[r-1]==0 && r>0) r--;
		
		double[] d1 = new double[r];
		for(int i=0;i<r;i++) d1[i] = d[i];
		return d1;
	}
}
