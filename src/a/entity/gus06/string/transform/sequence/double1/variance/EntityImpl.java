package a.entity.gus06.string.transform.sequence.double1.variance;

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
		
		double count = (double) array.length;
		
		double sum1 = sum1(array);
		double avg = sum1/count;
		
		double sum2 = sum2(array,avg);
		double variance = sum2/count;
		
		return ""+variance;
	}
	
	
	
	private double sum1(double[] array)
	{
		double sum = 0;
		for(double n:array) sum =+ n;
		return sum;
	}
	
	private double sum2(double[] array, double avg)
	{
		double sum = 0;
		for(double n:array) sum =+ (n-avg)*(n-avg);
		return sum;
	}
}
