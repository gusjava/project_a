package a.entity.gus06.sys.expression1.apply.op._block_to_p;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180221";}


	private Service findBlock;
	private Service getArgs;
	private Service buildTagMap;
	private Service executeTag;
	private Service blockExecutor;
	private Service findContext;
	private Service findTag;
	
	public EntityImpl() throws Exception
	{
		findBlock = Outside.service(this,"gus06.sys.script1.access.context.block1.find.default0");
		getArgs = Outside.service(this,"gus06.sys.script1.tool.execute.params.handler1.a.args");
		buildTagMap = Outside.service(this,"gus06.sys.script1.analyze1.buildtag.element");
		executeTag = Outside.service(this,"gus06.sys.script1.tool.execute.tag");
		blockExecutor = Outside.service(this,"gus06.sys.script1.executor.type.el.r.block");
		findContext = Outside.service(this,"gus06.sys.script1.access.opmap.context");
		findTag = Outside.service(this,"gus06.sys.script1.access.context.execution.current");
	}



	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		Map opMap = (Map) o[1];
		
		Map context = (Map) findContext.t(opMap);
		
		if(value==null) return null;
		if(value instanceof String) return new Holder(context, (String) value);
			
		throw new Exception("Invalid data type: "+value.getClass().getName());
	}
	
	
	
	private class Holder implements P
	{
		private Map context;
		private String blockName;
		private Map tag1;
		
		public Holder(Map context, String blockName) throws Exception
		{
			this.context = context;
			this.blockName = blockName;
			this.tag1 = (Map) findBlock.t(new Object[]{context,blockName});
		}
		
		public void p(Object obj) throws Exception
		{handle((Map) obj);}
		
		private void handle(Map args) throws Exception
		{
			if(tag1==null) return;
			
			Map tag = (Map) findTag.t(context);
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
}
