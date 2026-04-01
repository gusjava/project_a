package a.entity.gus06.sys.script1.executor.type.text;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150829";}
	
	public static final String TRANS_TEXT = "trans_text";


	private Service getValue;
	private Service getOutput;

	public EntityImpl() throws Exception
	{
		getValue = Outside.service(this,"gus06.sys.script1.access.tag.value1");
		getOutput = Outside.service(this,"gus06.sys.script1.access.context.output0");
	}

	
	public Object t(Object obj) throws Exception
	{return new Executor((Map) obj);}
	
	
	
	private class Executor implements P
	{
		private Map tag;
		public Executor(Map tag) {this.tag = tag;}
		
		public void p(Object obj) throws Exception
		{
			Map context = (Map) obj;
			
			String value = (String) getValue.t(tag);
			
			P output = (P) getOutput.t(context);
			if(output==null) return;
			
			boolean enabled = ((F) output).f("text");
			if(!enabled) return;
			
			T t = (T) get(context,TRANS_TEXT);
			if(t!=null) value = (String) t.t(value);
			
			output.p(value);
		}
	}
	
	
	private Object get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
}
