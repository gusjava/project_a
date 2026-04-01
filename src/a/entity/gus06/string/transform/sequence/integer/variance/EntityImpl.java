package a.entity.gus06.string.transform.sequence.integer.variance;

import a.framework.*;
import java.util.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150928";}


	private Service toIntArray;
	
	
	public EntityImpl() throws Exception
	{
		toIntArray = Outside.service(this,"gus06.convert.stringtointarray.seq");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		int[] array = (int[]) toIntArray.t(s);
		
		double count = (double) array.length;
		
		double sum1 = sum1(array);
		double avg = sum1/count;
		
		double sum2 = sum2(array,avg);
		double variance = sum2/count;
		
		return ""+variance;
	}
	
	
	
	private double sum1(int[] array)
	{
		double sum = 0;
		for(int n:array) sum += n;
		return sum;
	}
	
	private double sum2(int[] array, double avg)
	{
		double sum = 0;
		for(int n:array) sum += (n-avg)*(n-avg);
		return sum;
	}
}
