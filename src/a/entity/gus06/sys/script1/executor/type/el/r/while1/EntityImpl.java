package a.entity.gus06.sys.script1.executor.type.el.r.while1;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151111";}
	
	public static final String K_VAR = "var";
	public static final String K_UNTIL = "until";
	public static final String K_SKIP = "skip";
	public static final String K_KEEP = "keep";
	public static final String K_MIN = "min";
	public static final String K_MAX = "max";
	public static final String K_WAIT = "wait";
	
	public static final int LIMIT = 100000;

	
	private Service evalAsBoolean;
	private Service executePart1;
	private Service executePart2;
	private Service wrapping1;
	private Service getMain;

	public EntityImpl() throws Exception
	{
		evalAsBoolean = Outside.service(this,"gus06.sys.script1.context.evaluate.boolean1");
		executePart1 = Outside.service(this,"gus06.sys.script1.tool.execute.content.part1");
		executePart2 = Outside.service(this,"gus06.sys.script1.tool.execute.content.part2");
		wrapping1 = Outside.service(this,"gus06.sys.script1.tool.execute.wrapping1");
		getMain = Outside.service(this,"gus06.sys.script1.tool.execute.params.handler1.a.main");
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
			
			
			Boolean while1 = (Boolean) main;
			String var = (String) get(data,K_VAR);
			String until1 = (String) get(data,K_UNTIL);
			String skip1 = (String) get(data,K_SKIP);
			String keep = (String) get(data,K_KEEP);
			Integer min = (Integer) get(data,K_MIN);
			Integer max = (Integer) get(data,K_MAX);
			Integer wait = (Integer) get(data,K_WAIT);
			
			int limit = max!=null ? max.intValue() : LIMIT;
			
			int k = 0;
			
			String mName1 = var!=null?var:"i";
			String mName2 = mName1 + "_";
			
			if(min!=null)
			for(int i=0;i<min.intValue();i++)
			{
				Map m = new HashMap();
				m.put("index",Integer.valueOf(k));
				m.put("index1",Integer.valueOf(k+1));
				m.put("first",Boolean.valueOf(k==0));
				m.put("even",Boolean.valueOf(k%2==0));
				m.put("odd",Boolean.valueOf(k%2==1));
				
				pool1.put(mName1,Integer.valueOf(i));
				pool1.put(mName2,m);
					
				if(wait!=null)
				try{Thread.sleep(wait);}
				catch(Exception e){}
				
				executePart1.p(new Map[]{tag,context});
				k++;
			}
			
			if(k>0)	while1 = evalMain(context,tag);
			
			while(while1.booleanValue() && k<=limit)
			{
				if(until1!=null && isTrue(context,until1)) break;
				
				if(skip1==null || !isTrue(context,skip1))
				if(keep==null || isTrue(context,keep))
				{
					Map m = new HashMap();
					m.put("index",Integer.valueOf(k));
					m.put("index1",Integer.valueOf(k+1));
					m.put("first",Boolean.valueOf(k==0));
					m.put("even",Boolean.valueOf(k%2==0));
					m.put("odd",Boolean.valueOf(k%2==1));
					
					pool1.put(mName1,Integer.valueOf(k));
					pool1.put(mName2,m);
					
					if(wait!=null)
					try{Thread.sleep(wait);}
					catch(Exception e){}
					
					executePart1.p(new Map[]{tag,context});
				}
				
				k++;
				while1 = evalMain(context,tag);
			}
			
			if(k==0) executePart2.p(new Map[]{tag,context});
			else if(k>LIMIT && max==null) throw new Exception("Infinite loop detected");
		}
	}
	
	
	private Object get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
	
	
	private boolean isTrue(Map context, String rule) throws Exception
	{
		Boolean b = (Boolean) evalAsBoolean.t(new Object[]{context,rule});
		return b.booleanValue();
	}
	
	
	private Boolean evalMain(Map context, Map tag) throws Exception
	{
		return (Boolean) getMain.t(new Object[]{context,tag});
	}
}
