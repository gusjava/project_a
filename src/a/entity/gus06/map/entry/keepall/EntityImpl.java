package a.entity.gus06.map.entry.keepall;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20151125";}


	private Service builder;
	
	public EntityImpl() throws Exception
	{builder = Outside.service(this,"gus06.feature.op.col.findall.mapentry.f");}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map input = (Map) o[0];
		F filter = (F) o[1];
		
		T t = (T) builder.t(filter);
		Map output = (Map) t.t(input);
		
		input.clear();
		input.putAll(output);
	}
}
