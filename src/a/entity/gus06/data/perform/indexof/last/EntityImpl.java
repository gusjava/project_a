package a.entity.gus06.data.perform.indexof.last;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20221009";}

	
	private Service handleArray;
	private Service handleList;
	private Service handleString;

	public EntityImpl() throws Exception
	{
		handleArray = Outside.service(this,"gus06.array.indexof.last");
		handleList = Outside.service(this,"gus06.list.indexof.last");
		handleString = Outside.service(this,"gus06.string.indexof.last");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object data = o[0];
		Object element = o[1];
		
		if(data instanceof Object[]) return handleArray.t(o);
		if(data instanceof List) return handleList.t(o);
		if(data instanceof String) return handleString.t(new String[]{(String) data, ""+element});
		
		throw new Exception("Invalid data type: "+data.getClass().getName());
	}
}