package a.entity.gus06.math.equation1.resolve;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231101";}


	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof double[])
		{
			double[] coef = (double[]) obj;
			if(coef.length!=2) throw new Exception("Invalid coef number: "+coef.length);
			if(coef[0]==0) throw new Exception("Invalid null first coef");
			
			double a = coef[0];
			double b = coef[1];
			
			return new double[]{-b/a};
		}
		if(obj instanceof int[])
		{
			int[] coef = (int[]) obj;
			if(coef.length!=2) throw new Exception("Invalid coef number: "+coef.length);
			if(coef[0]==0) throw new Exception("Invalid null first coef");
			
			int a = coef[0];
			int b = coef[1];
			
			return new double[]{-b/a};
		}
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}