package a.entity.gus06.sys.expression1.apply.op._win_hdd_physerial;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20251126";}


	private Service perform;
	private Service buildFile;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.env.windows.hdd.phy.serialnumber");
		buildFile = Outside.service(this,"gus06.sys.expression1.file.build");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof File) return perform.t(obj);
		if(obj instanceof String) return perform.t(buildFile.t(obj));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
