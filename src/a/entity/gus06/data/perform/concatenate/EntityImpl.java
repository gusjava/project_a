package a.entity.gus06.data.perform.concatenate;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150708";}



	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof String[])
			return concatenate((String[]) obj);
		if(obj instanceof List[])
			return concatenate((List[]) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private String concatenate(String[] tt)
	{
		StringBuffer b = new StringBuffer();
		for(String t:tt) b.append(t);
		return b.toString();
	}
	
	private List concatenate(List[] tt)
	{
		List l = new ArrayList();
		for(List t:tt) l.addAll(t);
		return l;
	}
}
