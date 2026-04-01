package a.entity.gus06.string.transform.sequence.double1.max;

import a.framework.*;
import java.util.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150928";}


	private Service toDoubleArray;
	
	
	public EntityImpl() throws Exception
	{
		toDoubleArray = Outside.service(this,"gus06.convert.stringtodoublearray.seq");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		double[] array = (double[]) toDoubleArray.t(s);
		return ""+max(array);
	}
	
	private double max(double[] array)
	{
		double max = Double.MIN_VALUE;
		for(double n:array) if(n>max) max = n;
		return max;
	}
}
