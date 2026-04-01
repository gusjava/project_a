package a.entity.gus06.string.transform.sequence.integer.sum;

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
		return ""+sum(array);
	}
	
	private int sum(int[] array)
	{
		int sum = 0;
		for(int n:array) sum += n;
		return sum;
	}
}
