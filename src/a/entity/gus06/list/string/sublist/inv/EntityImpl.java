package a.entity.gus06.list.string.sublist.inv;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160519";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List list = (List) o[0];
		String offset = (String) o[1];
		
		int length = offset.length();
		
		List list1 = new ArrayList();
		for(int i=0;i<list.size();i++)
		{
			String value = (String) list.get(i);
			list1.add(offset+value);
		}
		return list1;
	}
}
