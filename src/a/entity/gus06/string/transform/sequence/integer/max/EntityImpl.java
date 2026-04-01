package a.entity.gus06.string.transform.sequence.integer.max;

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
		return ""+max(array);
	}
	
	private int max(int[] array)
	{
		int max = Integer.MIN_VALUE;
		for(int n:array) if(n>max) max = n;
		return max;
	}
}
