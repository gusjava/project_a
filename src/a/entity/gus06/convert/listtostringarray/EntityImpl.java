package a.entity.gus06.convert.listtostringarray;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160625";}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		List l = (List) obj;
		int number = l.size();
		
		String[] yy = new String[number];
		for(int i=0;i<number;i++) yy[i] = toString(l.get(i));
		return yy;
	}
	
	
	private String toString(Object o)
	{return o==null ? null : o.toString();}
}
