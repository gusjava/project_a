package a.entity.gus06.string.transform.line.join.trimed.comma;

import a.framework.*;
import java.util.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20260112";}
	
	public static final String DELIM1 = "\n";
	public static final String DELIM2 = ",";
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		String[] n = s.split(DELIM1,-1);
		
		StringBuffer b = new StringBuffer();
		
		for(int i=0;i<n.length;i++)
		b.append(n[i].trim()+DELIM2);
		
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		return b.toString();
	}
}
