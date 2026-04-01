package a.entity.gus06.sys.script1.executor.type.el.r.block.derivation;

import a.framework.*;
import java.util.Map;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160203";}
	
	public static final String K_DERIVED = "derived";


	private Service findBlock2;
	
	public EntityImpl() throws Exception
	{
		findBlock2 = Outside.service(this,"gus06.sys.script1.access.context.block2.findall");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Map context = (Map) o[0];
		Map tag = (Map) o[1];
		String name = (String) o[2];
		
		
		List found = (List) findBlock2.t(new Object[]{context,name});
		for(int i=0;i<found.size();i++)
		{
			Map tag2 = (Map) found.get(i);
			tag2.put(K_DERIVED,tag);
			tag = tag2;
		}
		return tag;
	}
}
