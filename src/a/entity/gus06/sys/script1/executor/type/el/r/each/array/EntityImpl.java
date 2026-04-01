package a.entity.gus06.sys.script1.executor.type.el.r.each.array;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20151115";}
	
	public static final String K_VAR = "var";
	public static final String K_WHILE = "while";
	public static final String K_UNTIL = "until";
	public static final String K_SKIP = "skip";
	public static final String K_KEEP = "keep";
	public static final String K_MAX = "max";
	public static final String K_OFFSET = "offset";
	public static final String K_WAIT = "wait";
	public static final String K_SORT = "sort";
	public static final String K_INDEX = "index";

	
	private Service executePart1;
	private Service executePart2;
	private Service evalAsBoolean;

	public EntityImpl() throws Exception
	{
		executePart1 = Outside.service(this,"gus06.sys.script1.tool.execute.content.part1");
		executePart2 = Outside.service(this,"gus06.sys.script1.tool.execute.content.part2");
		evalAsBoolean = Outside.service(this,"gus06.sys.script1.context.evaluate.boolean1");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=5) throw new Exception("Wrong data number: "+o.length);
			
		Map context = (Map) o[0];
		Map tag = (Map) o[1];
		Map pool1 = (Map) o[2];
		Object main = o[3];
		Map data = (Map) o[4];
		
		String var = (String) get(data,K_VAR);
		String while1 = (String) get(data,K_WHILE);
		String until1 = (String) get(data,K_UNTIL);
		String skip1 = (String) get(data,K_SKIP);
		String keep = (String) get(data,K_KEEP);
		Integer max = (Integer) get(data,K_MAX);
		Integer offset = (Integer) get(data,K_OFFSET);
		Integer wait = (Integer) get(data,K_WAIT);
		Object sort = get(data,K_SORT);
		Integer index = (Integer) get(data,K_INDEX);
		
		List struct = sortStruct((Object[]) main,sort);
		int size = struct.size();
		
		String name_i = getIndexName(var);
		String name_v = getElementName(var);
		String name_i_ = name_i + "_";
				
		for(int i=0;i<size;i++)
		if(offset==null || offset.intValue()<=i)
		if(index==null || index.intValue()==i)
		{
			Object value = struct.get(i);
			Object previous = i>0?struct.get(i-1):null;
			Object next = i<size-1?struct.get(i+1):null;
			
			Map m = new HashMap();
			
			m.put("index",Integer.valueOf(i));
			m.put("index1",Integer.valueOf(i+1));
			m.put("size",Integer.valueOf(size));
			m.put("first",Boolean.valueOf(i==0));
			m.put("last",Boolean.valueOf(i==size-1));
			m.put("even",Boolean.valueOf(i%2==0));
			m.put("odd",Boolean.valueOf(i%2==1));
			m.put("progress",(i+1)+"/"+size);
			m.put("value",value);
			m.put("previous",previous);
			m.put("next",next);
			
			pool1.put(name_i,Integer.valueOf(i));
			pool1.put(name_v,value);
			pool1.put(name_i_,m);
			
			if(while1!=null && !isTrue(context,while1)) return;
			if(until1!=null && isTrue(context,until1)) return;
			if(max!=null && i>=max.intValue()) return;
					
			if(skip1==null || !isTrue(context,skip1))
			if(keep==null || isTrue(context,keep))
			{
				if(wait!=null)
				try{Thread.sleep(wait);}
				catch(Exception e){}
				
				executePart1.p(new Map[]{tag,context});
			}
		}
		if(struct.isEmpty())
		{
			executePart2.p(new Map[]{tag,context});
		}
	}
	
	
	private Object get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
	
	
	
	private String getElementName(String var)
	{
		if(var==null) return "v";
		if(!var.contains(":")) return var;
		return var.split(":")[0];
	}
	
	
	private String getIndexName(String var)
	{
		if(var==null) return "i";
		if(!var.contains(":")) return "i";
		return var.split(":")[1];
	}
	
	
	private boolean isTrue(Map context, String rule) throws Exception
	{
		Boolean b = (Boolean) evalAsBoolean.t(new Object[]{context,rule});
		return b.booleanValue();
	}
	
	
	private List sortStruct(Object[] struct, Object sort)
	{
		List l = Arrays.asList(struct);
		if(sort==null) return l;
		
		if(sort.equals(Boolean.TRUE))
		{
			Collections.sort(l);
		}
		else if(sort.equals("inv"))
		{
			Collections.sort(l);
			Collections.reverse(l);
		}
		return l;
	}
}