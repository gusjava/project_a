package a.entity.gus06.sys.script1.tool.execute.content.part2;

import a.framework.*;
import java.util.Map;
import java.util.List;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150902";}
	
	public static final String ELSE = "else";
	public static final String ELSEIF = "elseif";


	private Service execute;
	private Service getContent;
	private Service getName;
	private Service getParams;
	private Service evaluate;

	public EntityImpl() throws Exception
	{
		execute = Outside.service(this,"gus06.sys.script1.tool.execute.tag");
		getContent = Outside.service(this,"gus06.sys.script1.access.tag.content1");
		getName = Outside.service(this,"gus06.sys.script1.access.tag.name0");
		getParams = Outside.service(this,"gus06.sys.script1.access.tag.params1");
		evaluate = Outside.service(this,"gus06.sys.script1.context.evaluate");
	}
	
	
	
	
	public void p(Object obj) throws Exception
	{
		Map[] o = (Map[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map tag = o[0];
		Map context = o[1];
		
		List content = (List) getContent.t(tag);
		boolean elseFound = false;
		
		for(int i=0;i<content.size();i++) 
		{
			Map child = (Map) content.get(i);
			String name = (String) getName.t(child);
			
			if(elseFound)
			{
				if(name!=null && (name.equals(ELSE) || name.equals(ELSEIF))) break;
				execute.p(new Map[]{child,context});
			}
			else
			{
				if(name!=null && name.equals(ELSE))
				{
					elseFound = true;
				}
				else if(name!=null && name.equals(ELSEIF))
				{
					String params = (String) getParams.t(child);
					elseFound = evaluate(context,params);
				}
			}
		}
	}
	
	
	
	private boolean evaluate(Map context, String exp) throws Exception
	{
		Boolean r = (Boolean) evaluate.t(new Object[]{context,exp});
		return r.booleanValue();
	}
}
