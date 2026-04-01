package a.entity.gus06.sys.script1.executor.type.el.z.call;

import a.framework.*;
import java.util.*;

public class EntityImpl implements Entity, T {
	
	public String creationDate() {return "20151103";}
	
	public static final String K_ARGS = "args";
	
	
	private Service findBlock;
	private Service wrapping1;
	private Service getArgs;
	private Service buildTagMap;
	private Service executeTag;
	private Service blockExecutor;


	public EntityImpl() throws Exception
	{
		findBlock = Outside.service(this,"gus06.sys.script1.access.context.block1.find.default0");
		wrapping1 = Outside.service(this,"gus06.sys.script1.tool.execute.wrapping1");
		getArgs = Outside.service(this,"gus06.sys.script1.tool.execute.params.handler1.a.args");
		buildTagMap = Outside.service(this,"gus06.sys.script1.analyze1.buildtag.element");
		executeTag = Outside.service(this,"gus06.sys.script1.tool.execute.tag");
		blockExecutor = Outside.service(this,"gus06.sys.script1.executor.type.el.r.block");
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
			
			
			String blockName = (String) main;
			Map args = (Map) get(data,K_ARGS);
			
			Map tag1 = (Map) findBlock.t(new Object[]{context,blockName});
			if(tag1==null) return;
			
			Map args1 = (Map) getArgs.t(new Object[]{context,tag1});
			List content1 = (List) tag1.get("content");
			String name1 = (String) tag1.get("name");
			String type1 = (String) tag1.get("type");
			
			Map tag2 = (Map) buildTagMap.t("*");
			
			tag2.put("name",name1);
			tag2.put("type",type1);
			tag2.put("params","'"+blockName+"'");
			tag2.put("content",content1);
			tag2.put("executor",blockExecutor.t(tag2));
			tag2.put("parent",tag);
			
			for(int i=0;i<content1.size();i++)
			{
				Map c = (Map) content1.get(i);
				c.put("parent",tag2);
			}
			
			Map newData = new HashMap();
			if(args1!=null) newData.putAll(args1);
			if(args!=null) newData.putAll(args);
			
			tag2.put("data",newData);
			
			
			executeTag.p(new Map[]{tag2,context});
		}
	}
	
	
	
	private Object get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
}