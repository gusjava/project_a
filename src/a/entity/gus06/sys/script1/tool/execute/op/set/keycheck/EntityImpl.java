package a.entity.gus06.sys.script1.tool.execute.op.set.keycheck;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160121";}


	private Service reserved;
	
	public EntityImpl() throws Exception
	{reserved = Outside.service(this,"gus06.sys.script1.tool.execute.tag.prepare.reserved");}
	
	
	public void p(Object obj) throws Exception
	{
		String key = (String) obj;
		
		if(key.equals("null")) throw new Exception("Forbidden name: "+key);
		if(key.equals("true")) throw new Exception("Forbidden name: "+key);
		if(key.equals("false")) throw new Exception("Forbidden name: "+key);
		
		if(isDouble(key)) throw new Exception("Forbidden name: "+key);
		
		List list = (List) reserved.g();
		for(int i=0;i<list.size();i++)
		{
			String w = (String) list.get(i);
			if(key.equals(w)) throw new Exception("Forbidden name: "+key);
		}
	}
	
	private boolean isDouble(String key)
	{
		try{Double.parseDouble(key);return true;}
		catch(NumberFormatException e){}
		return false;
	}
}
