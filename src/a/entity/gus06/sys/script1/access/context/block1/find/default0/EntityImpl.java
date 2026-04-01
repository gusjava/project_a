package a.entity.gus06.sys.script1.access.context.block1.find.default0;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160221";}

	public static final String UNDEFINED = "undefined";
	
	private Service findBlock;
	
	public EntityImpl() throws Exception
	{
		findBlock = Outside.service(this,"gus06.sys.script1.access.context.block1.find");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map context = (Map) o[0];
		String name = (String) o[1];
		
		Map tag = (Map) findBlock.t(new Object[]{context,name});
		if(tag!=null) return tag;
		
		return (Map) findBlock.t(new Object[]{context,UNDEFINED});
	}
}
