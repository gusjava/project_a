package a.entity.gus06.string.transform.line.rm.common.sequence.elements;

import a.framework.*;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160802";}
	
	public static final String DELIM = "\n";


	private Service intersection;
	
	public EntityImpl() throws Exception
	{intersection = Outside.service(this,"gus06.set.setarray.toset.intersection");}

	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		String[] n = s.split(DELIM,-1);
		int length = n.length;
		
		Set[] sets = new HashSet[length];
		for(int i=0;i<length;i++) sets[i] = lineToSet(n[i]);
		
		Set set1 = (Set) intersection.t(sets);
		
		StringBuffer b = new StringBuffer();
		for(int i=0;i<length;i++)
		{
			sets[i].removeAll(set1);
			String line1 = setToLine(sets[i]);
			b.append(line1+DELIM);
		}
		
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		return b.toString();
	}
	
	
	
	private Set lineToSet(String line)
	{
		String[] n = line.split(";");
		return new HashSet(Arrays.asList(n));
	}
	
	
	private String setToLine(Set set)
	{
		List l = new ArrayList(set);
		Collections.sort(l);
		StringBuffer b = new StringBuffer();
		for(int i=0;i<l.size();i++) b.append(l.get(i)+";");
		
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		return b.toString();
	}
}
