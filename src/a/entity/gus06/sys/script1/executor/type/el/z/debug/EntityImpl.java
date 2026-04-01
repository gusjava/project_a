package a.entity.gus06.sys.script1.executor.type.el.z.debug;

import a.framework.*;
import java.util.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151111";}


	private Service getOutput;
	private Service wrapping1;

	public EntityImpl() throws Exception
	{
		getOutput = Outside.service(this,"gus06.sys.script1.access.context.output0");
		wrapping1 = Outside.service(this,"gus06.sys.script1.tool.execute.wrapping1");
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
			wrapping1.p(new Object[]{context,tag,new Wrap()});
		}
	}
	
	
	
	
	private class Wrap implements P
	{
		public void p(Object obj) throws Exception
		{
			Object[] o = (Object[]) obj;
			if(o.length!=5) throw new Exception("Wrong data number: "+o.length);
			
			Map context = (Map) o[0];
			Map tag = (Map) o[1];
			Map pool1 = (Map) o[2];
			Object main = o[3];
			Map data = (Map) o[4];
			
			String name = (String) main;
			P p = (P) getOutput.t(context);
			
			print(p,"______________");
			print(p,"DEBUG "+name);
			
			print(p,display(context,"level"));
			print(p,"");
			
			print(p,"______________");
		}
	}
	
	
	
	
	
	private String display(Map map, String key)
	{
		Object value = map.get(key);
		return key+"="+value;
	}
	
	
	private String display(Object obj)
	{
		if(obj==null) return "null";
		return obj.getClass().getName();
	}
	
	
	private Object get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
	
	private boolean has(Map map, String key)
	{return map.containsKey(key);}
	
	
	
	
	
	private void print(P p, Map m, String offset) throws Exception
	{
		ArrayList keys = new ArrayList(m.keySet());
		Collections.sort(keys);
		for(int i=0;i<keys.size();i++)
		{
			String key = (String) keys.get(i);
			print(p,offset+display(m,key));
		}
	}
	
	private void print(P p, String s) throws Exception
	{p.p(s+"\n");}
}
