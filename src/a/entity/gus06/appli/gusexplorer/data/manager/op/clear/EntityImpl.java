package a.entity.gus06.appli.gusexplorer.data.manager.op.clear;

import a.framework.*;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151017";}

	public static final String ACTION = "clear";


	private Service extractPath;
	
	public EntityImpl() throws Exception
	{
		extractPath = Outside.service(this,"gus06.file.lnk.extract.path");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List list = (List) o[0];
		Object data = o[1];
		
		return perform(list);
	}
	
	
	private Map perform(List l) throws Exception
	{
		if(l.isEmpty()) return null;
		
		l.clear();
		
		Map m = new HashMap();
		m.put("action",ACTION);
		
		return m;
	}
}
