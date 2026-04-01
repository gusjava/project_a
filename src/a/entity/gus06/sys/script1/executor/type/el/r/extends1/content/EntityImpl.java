package a.entity.gus06.sys.script1.executor.type.el.r.extends1.content;

import a.framework.*;
import java.util.Map;
import java.util.List;

public class EntityImpl implements Entity, P {
	
	public String creationDate() {return "20160202";}

	public static final String K_MAIN = "main";
	public static final String T_ELEMENT = "element";
	
	
	private Service getContent;
	private Service getName;
	private Service getType;
	private Service findMain;
	private Service getBlocks2;
	private Service prepareTag;


	public EntityImpl() throws Exception
	{
		getContent = Outside.service(this,"gus06.sys.script1.access.tag.content1");
		getName = Outside.service(this,"gus06.sys.script1.access.tag.name1");
		getType = Outside.service(this,"gus06.sys.script1.access.tag.type1");
		findMain = Outside.service(this,"gus06.sys.script1.tool.execute.params.handler1.a.main");
		getBlocks2 = Outside.service(this,"gus06.sys.script1.access.context.block2.latest");
		prepareTag = Outside.service(this,"gus06.sys.script1.tool.execute.tag.prepare");
		
	}
	
		
	public void p(Object obj) throws Exception
	{
		Map[] m = (Map[]) obj;
		if(m.length!=2) throw new Exception("Wrong data number: "+m.length);
		
		Map context = m[0];
		Map tag = m[1];
			
		Map blocks2 = (Map) getBlocks2.t(context);
		List content = (List) getContent.t(tag);
		
		for(int i=0;i<content.size();i++)
		{
			Map child = (Map) content.get(i);
			String tagType = (String) getType.t(child);
			
			if(tagType.equals(T_ELEMENT))
			{
				String tagName = (String) getName.t(child);
				
				if(tagName.equals("block"))
				{
					String blockName = (String) findMain.t(new Object[]{context,child});
					prepareTag.p(new Map[]{child,context});
					blocks2.put(blockName,child);
				}
				else throw new Exception("Invalid child inside extends tag: "+tagName);
			}
		}
	}
}
