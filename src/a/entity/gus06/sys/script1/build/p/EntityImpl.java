package a.entity.gus06.sys.script1.build.p;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160504";}
	
	public static final String INPUT = "input";


	private Service engine;
	private Service contextBuilder;


	public EntityImpl() throws Exception
	{
		engine = Outside.service(this,"gus06.sys.script1.engine");
		contextBuilder = Outside.service(this,"gus06.sys.script1.context.builder1.a");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		
		if(o.length==2) return new P1(o[0],(Map) o[1],null);
		if(o.length==3) return new P1(o[0],(Map) o[1],(Map) o[2]);
		
		throw new Exception("Wrong data number: "+o.length);
	}
	
	
	
	
	private class P1 implements P
	{
		private Object src;
		private Map opMap;
		private Map data;
		
		public P1(Object src, Map opMap, Map data)
		{
			this.src = src;
			this.opMap = opMap;
			this.data = data;
		}
		
		public void p(Object obj) throws Exception
		{
			Map input = new HashMap();
			if(data!=null) input.putAll(data);
			input.put(INPUT,obj);
			
			Map context1 = (Map) contextBuilder.t(new Object[]{input,null,opMap});
			engine.p(new Object[]{src,context1});
		}
	}
}
