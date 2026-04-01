package a.entity.gus06.sys.expression1.builder2.f.i;

import a.framework.*;
import java.util.Map;
import java.util.Collection;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20221008";}


	private Service mapToF;
	private Service colToF;

	public EntityImpl() throws Exception
	{
		mapToF = Outside.service(this,"gus06.map.contains.key.maptof.i");
		colToF = Outside.service(this,"gus06.collection.contains.coltof.i");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object data = o[0];
		Map opMap = (Map) o[1];
		
		return findF(data,opMap);
	}
	
	private F findF(Object data, Map opMap) throws Exception
	{
		if(data instanceof Map) 	return (F) mapToF.t(data);
		if(data instanceof Collection)	return (F) colToF.t(data);
		
		throw new Exception("Invalid data type: "+data.getClass().getName());
	}	
}