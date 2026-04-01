package a.entity.gus06.data.perform.random.set;

import a.framework.*;
import java.util.Set;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250706";}


	private Service randomList;
	
	public EntityImpl() throws Exception
	{
		randomList = Outside.service(this,"gus06.data.perform.random.list");
	}

	public Object t(Object obj) throws Exception
	{
		if(obj instanceof Set) return randomList.t(new ArrayList((Set) obj));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
