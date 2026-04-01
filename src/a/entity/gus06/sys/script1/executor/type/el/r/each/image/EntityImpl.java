package a.entity.gus06.sys.script1.executor.type.el.r.each.image;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.awt.image.BufferedImage;
import java.awt.Color;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20180412";}
	
	public static final String K_VAR = "var";
	public static final String K_WHILE = "while";
	public static final String K_UNTIL = "until";
	public static final String K_SKIP = "skip";
	public static final String K_KEEP = "keep";
	public static final String K_MAX = "max";
	public static final String K_WAIT = "wait";

	
	private Service executePart1;
	private Service executePart2;
	private Service evalAsBoolean;
	private Service toBufferedImage;
	
	public EntityImpl() throws Exception
	{
		executePart1 = Outside.service(this,"gus06.sys.script1.tool.execute.content.part1");
		executePart2 = Outside.service(this,"gus06.sys.script1.tool.execute.content.part2");
		evalAsBoolean = Outside.service(this,"gus06.sys.script1.context.evaluate.boolean1");
		toBufferedImage = Outside.service(this,"gus06.find.bufferedimage");
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
		Integer wait = (Integer) get(data,K_WAIT);
		
		BufferedImage struct = (BufferedImage) toBufferedImage.t(main);
		
		String name_i = getIndexName(var);
		String name_v = getElementName(var);
		String name_i_ = name_i + "_";
		
		int nb1 = struct.getWidth();
		int nb2 = struct.getHeight();
		int total = nb1*nb2;
				
		for(int i=0;i<nb1;i++)
		for(int j=0;j<nb2;j++)
		{
			Object value = new Color(struct.getRGB(i,j));
			int index = i*nb2+j;
			
			Map m = new HashMap();
			
			m.put("i",Integer.valueOf(i));
			m.put("j",Integer.valueOf(j));
			m.put("index",Integer.valueOf(index));
			m.put("index1",Integer.valueOf(index+1));
			m.put("size",Integer.valueOf(total));
			m.put("first",Boolean.valueOf(index==0));
			m.put("last",Boolean.valueOf(index==total-1));
			m.put("even",Boolean.valueOf(index%2==0));
			m.put("odd",Boolean.valueOf(index%2==1));
			m.put("progress",(i+1)+"/"+total);
			m.put("value",value);
			
			pool1.put(name_i,Integer.valueOf(index));
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
}
