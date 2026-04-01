package a.entity.gus06.sys.expression1.apply.op._txtfile_tail_catcher;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20221010";}


	private Service buildCatcher;
	
	public EntityImpl() throws Exception
	{
		buildCatcher = Outside.service(this,"gus06.sys.txtfiletail.build.catcher");
	}

	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof File) return buildCatcher.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}