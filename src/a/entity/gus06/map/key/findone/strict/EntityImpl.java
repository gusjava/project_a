package a.entity.gus06.map.key.findone.strict;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170514";}


	private Service builder;
	
	public EntityImpl() throws Exception
	{builder = Outside.service(this,"gus06.feature.op.col.findall.mapkey.f");}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map input = (Map) o[0];
		F filter = (F) o[1];
		
		T t = (T) builder.t(filter);
		Map r = (Map) t.t(input);
		if(r.size()!=1) throw new Exception("Many results found in strict mode");
		return r;
	}
}
