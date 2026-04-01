package a.entity.gus06.string.transform.sequence.order.shuffle;

import a.framework.*;
import java.util.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150927";}
	
	public static final String DELIM = ";";
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		String[] n = s.split(DELIM,-1);
		
		List list = Arrays.asList(n);
		Collections.shuffle(list);
		return toString(list);
	}
	
	private String toString(List list)
	{
		StringBuffer b = new StringBuffer();
		for(int i=0;i<list.size();i++)
		b.append(list.get(i)+DELIM);
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		return b.toString();
	}
}
