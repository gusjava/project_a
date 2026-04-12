package a.entity.gus06.tostring.list.join.n;

import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180427";}
	
	public static final String DELIM = "\n";

	public Object t(Object obj) throws Exception
	{return listToString((List) obj);}
	
	private String listToString(List list) throws Exception
	{
		StringBuffer b = new StringBuffer();
		for(int i=0;i<list.size();i++)
		{
			String el = "" + list.get(i);
			if(el.contains(DELIM)) throw new Exception("Invalid element syntax: "+el);
			b.append(el+DELIM);
		}
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		return b.toString();
	}
}
