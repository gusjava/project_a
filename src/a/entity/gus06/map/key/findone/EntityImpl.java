package a.entity.gus06.map.key.findone;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170514";}


	private Service builder;
	
	public EntityImpl() throws Exception
	{builder = Outside.service(this,"gus06.feature.op.col.findone.mapkey.f");}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map input = (Map) o[0];
		F filter = (F) o[1];
		
		T t = (T) builder.t(filter);
		Object[] r = (Object[]) t.t(input);
		if(r==null) return null;
		
		Map output = new HashMap();
		output.put(r[0],r[1]);
		return output;
	}
}
