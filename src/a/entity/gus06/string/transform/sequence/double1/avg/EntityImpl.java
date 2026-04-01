package a.entity.gus06.string.transform.sequence.double1.avg;

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
		
		double sum = sum(array);
		int count = array.length;
		
		double avg = sum/(double)count;
		return ""+avg;
	}
	
	private double sum(double[] array)
	{
		double sum = 0;
		for(double n:array) sum =+ n;
		return sum;
	}
}
