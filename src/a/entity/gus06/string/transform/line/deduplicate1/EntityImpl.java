package a.entity.gus06.string.transform.line.deduplicate1;

import a.framework.*;
import java.util.List;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20230222";}
	
	public static final String DELIM = "\n";
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		String[] n = s.split(DELIM,-1);
		List list = Arrays.asList(n);
		Set done = new HashSet();
		
		StringBuffer b = new StringBuffer();
		for(int i=0;i<n.length;i++)
		{
			String line = n[i];
			if(!done.contains(line))
			{
				b.append(line+DELIM);
				done.add(line);
			}
			else
			{
				String newLine = findNewLine(line,done,list);
				b.append(newLine+DELIM);
				done.add(newLine);
			}
		}
		
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		return b.toString();
	}
	
	private String findNewLine(String line, Set done, List list)
	{
		int k = 1;
		String newLine = line+" - "+k;
		while(done.contains(newLine) || list.contains(newLine))
		{
			k++;
			newLine = line+"-"+k;			
		}
		return newLine;
	}
}
