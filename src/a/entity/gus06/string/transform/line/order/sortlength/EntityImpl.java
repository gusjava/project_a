package a.entity.gus06.string.transform.line.order.sortlength;

import a.framework.*;
import java.util.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141105";}
	
	public static final String DELIM = "\n";
	
	private Service performSort;
	
	public EntityImpl() throws Exception
	{
		performSort = Outside.service(this,"gus06.collection.comparator.length1.sort");
	}
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		String[] n = s.split(DELIM,-1);
		
		performSort.p(n);
		
		StringBuffer b = new StringBuffer();
		for(int i=0;i<n.length;i++)
		b.append(n[i]+DELIM);
		
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		return b.toString();
	}
}