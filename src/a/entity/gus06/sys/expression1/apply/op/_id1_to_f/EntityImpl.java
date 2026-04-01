package a.entity.gus06.sys.expression1.apply.op._id1_to_f;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160922";}


	private Service id_to_file;
	private Service script_to_f;
	
	public EntityImpl() throws Exception
	{
		id_to_file = Outside.service(this,"gus06.sys.expression1.apply.op._id1_to_file");
		script_to_f = Outside.service(this,"gus06.sys.expression1.apply.op._script_to_f");
	}


	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object data = o[0];
		Map opMap = (Map) o[1];
		
		if(data==null) return null;
		if(data instanceof String) return find(data, opMap);
		
		throw new Exception("Invalid data type: "+data.getClass().getName());
	}
	
	
	
	private Object find(Object data, Map opMap) throws Exception
	{
		Object r = id_to_file.t(new Object[]{data,opMap});
		return script_to_f.t(new Object[]{r,opMap});
	}
}
