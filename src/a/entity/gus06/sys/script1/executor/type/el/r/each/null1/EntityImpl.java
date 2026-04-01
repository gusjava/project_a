package a.entity.gus06.sys.script1.executor.type.el.r.each.null1;

import a.framework.*;
import java.util.Map;
import java.util.List;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160125";}

	
	private Service executePart2;

	public EntityImpl() throws Exception
	{
		executePart2 = Outside.service(this,"gus06.sys.script1.tool.execute.content.part2");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=5) throw new Exception("Wrong data number: "+o.length);
			
		Map context = (Map) o[0];
		Map tag = (Map) o[1];
		Map pool1 = (Map) o[2];
		Object main = o[3];
		Map data = (Map) o[4];
		
		executePart2.p(new Map[]{tag,context});	
	}
}
