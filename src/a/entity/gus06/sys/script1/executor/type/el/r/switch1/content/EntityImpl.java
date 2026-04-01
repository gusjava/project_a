package a.entity.gus06.sys.script1.executor.type.el.r.switch1.content;

import a.framework.*;
import java.util.Map;
import java.util.List;

public class EntityImpl implements Entity, P {
	
	public String creationDate() {return "20160305";}
	
	public static final String KEY_CASES = "cases";
	public static final String KEY_ELSE = "else";

	
	
	private Service getContent;
	private Service execute;
	private Service analyze;


	public EntityImpl() throws Exception
	{
		getContent = Outside.service(this,"gus06.sys.script1.access.tag.content1");
		execute = Outside.service(this,"gus06.sys.script1.tool.execute.tag");
		analyze = Outside.service(this,"gus06.sys.script1.executor.type.el.r.switch1.content.analyze");
	}
	
		
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Map context = (Map) o[0];
		Map tag = (Map) o[1];
		Object main = o[2];
		
		
		List content = (List) getContent.t(tag);
		Map map = (Map) analyze.t(new Object[]{context,content});
		
		List cases = (List) get(map,KEY_CASES);
		List elseC = (List) get(map,KEY_ELSE);
		
		boolean found = false;
		
		if(cases!=null) for(int i=0;i<cases.size();i++)
		{
			Object[] case1 = (Object[]) cases.get(i);
			F filter = (F) case1[0];
			List caseC = (List) case1[1];
			
			if(filter.f(main))
			{
				found = true;
				execute(caseC,context);
			}
		}
		
		if(elseC!=null && !found)
		{
			execute(elseC,context);
		}
	}
	
	
	
	private Object get(Map map, Object key)
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
	
	
	private void execute(List content, Map context) throws Exception
	{
		for(int i=0;i<content.size();i++)
		{
			Map child = (Map) content.get(i);
			execute.p(new Map[]{child,context});
		}
	}
}
