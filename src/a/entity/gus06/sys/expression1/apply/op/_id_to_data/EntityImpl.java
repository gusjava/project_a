package a.entity.gus06.sys.expression1.apply.op._id_to_data;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231127";}
	
	public static final String C_FILE_MAPPER = "file_mapper";


	private Service getContext;

	public EntityImpl() throws Exception
	{
		getContext = Outside.service(this,"gus06.sys.script1.access.opmap.context");
	}
	

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object data = o[0];
		Map opMap = (Map) o[1];
		
		if(data==null) return null;
		if(data instanceof String) return findData((String) data, opMap);
		
		throw new Exception("Invalid data type: "+data.getClass().getName());
	}
	
	
	
	private Object findData(String id, Map opMap) throws Exception
	{
		Map context = (Map) getContext.t(opMap);
		
		R r = (R) get(context,C_FILE_MAPPER);
		if(r==null) throw new Exception("File mapper not found inside context map");
		
		return r.r("data:"+id);
	}
	
	
	private Object get(Map m, String k)
	{return m.containsKey(k)?m.get(k):null;}
}