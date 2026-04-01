package a.entity.gus06.sys.script1.engine.fromtext;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20150829";}
	
	
	private Service retrieveBuilder;
	private Service retrieveExecutor;
	
	public EntityImpl() throws Exception
	{
		retrieveBuilder = Outside.service(this,"gus06.sys.script1.access.context.builder");
		retrieveExecutor = Outside.service(this,"gus06.sys.script1.access.context.executor");
	}

	
	
	public void p(Object obj) throws Exception
	{t(obj);}
	
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String input = (String) o[0];
		Map context = (Map) o[1];
		
		T builder = (T) retrieveBuilder.t(context);
		P executor = (P) retrieveExecutor.t(context);
		
		Map tag = (Map) builder.t(input);
		executor.p(new Map[]{tag,context});
		
		return tag;
	}
}
