package a.entity.gus06.sys.expression1.apply.op._startdate_script;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170329";}

	public static final String T = "constant";


	private Service findDate;
	private Service findContext;
		
	public EntityImpl() throws Exception
	{
		findDate = Outside.service(this,"gus06.sys.script1.access.context.startdate1");
		findContext = Outside.service(this,"gus06.sys.script1.access.opmap.context");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map opMap = (Map) o[1];
		Map context = (Map) findContext.t(opMap);
		return findDate.t(context);
	}
}
