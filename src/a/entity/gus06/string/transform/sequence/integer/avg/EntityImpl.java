package a.entity.gus06.string.transform.sequence.integer.avg;

import a.framework.*;
import java.util.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150927";}


	private Service toIntArray;
	
	
	public EntityImpl() throws Exception
	{
		toIntArray = Outside.service(this,"gus06.convert.stringtointarray.seq");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		int[] array = (int[]) toIntArray.t(s);
		
		int sum = sum(array);
		int count = array.length;
		
		double avg = (double)sum/(double)count;
		return ""+avg;
	}
	
	private int sum(int[] array)
	{
		int sum = 0;
		for(int n:array) sum += n;
		return sum;
	}
}
