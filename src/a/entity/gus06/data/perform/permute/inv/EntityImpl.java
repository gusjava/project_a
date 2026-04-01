package a.entity.gus06.data.perform.permute.inv;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160131";}
	
	
	public void p(Object obj) throws Exception
	{
		if(obj instanceof StringBuffer)
		{permuteInv((StringBuffer) obj);return;}
		
		if(obj instanceof List)
		{permuteInv((List) obj);return;}
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private void permuteInv(StringBuffer b)
	{
		if(b.length()==0) return;
		b.append(b.charAt(0));
		b.deleteCharAt(0);
	}
	
	private void permuteInv(List l)
	{
		if(l.isEmpty()) return;
		l.add(l.get(0));
		l.remove(0);
	}
}
