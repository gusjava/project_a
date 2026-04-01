package a.entity.gus06.sys.script1.tool.execute.content.all;

import a.framework.*;
import java.util.Map;
import java.util.List;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150829";}


	private Service execute;
	private Service getContent;

	public EntityImpl() throws Exception
	{
		execute = Outside.service(this,"gus06.sys.script1.tool.execute.tag");
		getContent = Outside.service(this,"gus06.sys.script1.access.tag.content1");
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		Map[] o = (Map[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map tag = o[0];
		Map context = o[1];
		
		List content = (List) getContent.t(tag);
		
		for(int i=0;i<content.size();i++)
		{
			Map child = (Map) content.get(i);
			execute.p(new Map[]{child,context});
		}
	}
}
