package a.entity.gus06.sys.script1.tool.execute.tag;

import a.framework.*;
import java.util.Map;
import java.io.File;

public class EntityImpl implements Entity, P {
	
	public String creationDate() {return "20150829";}
	

	private Service getExecutor;
	private Service prepareExecution;
	private Service modifyUserDir;


	public EntityImpl() throws Exception
	{
		getExecutor = Outside.service(this,"gus06.sys.script1.access.tag.executor1");
		prepareExecution = Outside.service(this,"gus06.sys.script1.tool.execute.tag.prepare");
		modifyUserDir = Outside.service(this,"gus06.system.prop.userdir.modify");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Map[] o = (Map[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map tag = o[0];
		Map context = o[1];
		
		boolean ok = prepareExecution.f(new Map[]{tag,context});
		if(!ok) return;
		
		try
		{
			P p = (P) getExecutor.t(tag);
			p.p(context);
		}
		catch(Exception e)
		{
			String message = "Failed to execute tag ["+tag+"]";
			throw new Exception(message,e);
		}
	}
	
	
	private String toString(Map tag)
	{
		StringBuffer b = new StringBuffer();
		b.append(tag);
		while(tag.containsKey("parent"))
		{
			tag = (Map) tag.get("parent");
			b.append("."+tag);
		}
		return b.toString();
	}
}