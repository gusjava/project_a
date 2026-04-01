package a.entity.gus06.sys.expression1.apply.op._shuffle;

import a.framework.*;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151110";}


	private Service strShuffle;
	
	public EntityImpl() throws Exception
	{
		strShuffle = Outside.service(this,"gus06.string.transform.character.order.shuffle");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof String) return strShuffle.t((String) obj);
		if(obj instanceof List) return shuffle((List) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private List shuffle(List list)
	{
		List list1 = new ArrayList(list);
		Collections.shuffle(list1);
		return list1;
	}
}
