package a.entity.gus06.sys.filetool.ext.library1.perform.paste3.image;

import a.framework.*;
import java.util.Map;
import java.util.List;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20220602";}
	
	private Service perform;
	private Service convert;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.sys.filetool.ext.library1.perform.paste1.files");
		convert = Outside.service(this,"gus06.sys.clipboard1.g.listfiles.convertimage");
	}
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map map = (Map) o[0];
		List list = (List)  convert.t(o[1]);
		return perform.f(new Object[]{map,list});
	}
}