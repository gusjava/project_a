package a.entity.gus06.data.perform.permute;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160131";}
	
	
	public void p(Object obj) throws Exception
	{
		if(obj instanceof StringBuffer)
		{permute((StringBuffer) obj);return;}
		
		if(obj instanceof List)
		{permute((List) obj);return;}
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private void permute(StringBuffer b)
	{
		if(b.length()==0) return;
		b.insert(0,b.charAt(b.length()-1));
		b.deleteCharAt(b.length()-1);
	}
	
	private void permute(List l)
	{
		if(l.isEmpty()) return;
		l.add(0,l.get(l.size()-1));
		l.remove(l.size()-1);
	}
}
