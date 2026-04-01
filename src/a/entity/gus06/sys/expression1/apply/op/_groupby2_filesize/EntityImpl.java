package a.entity.gus06.sys.expression1.apply.op._groupby2_filesize;

import a.framework.*;
import java.util.List;
import java.util.Set;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180310";}


	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.data.perform.groupby2.filesize");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		
		if(value==null) return null;
		
		if(value instanceof List) return perform.t(value);
		if(value instanceof Set) return perform.t(value);
		if(value instanceof File[]) return perform.t(value);
		if(value instanceof File) return perform.t(value);
		
		throw new Exception("Invalid data type: "+value.getClass().getName());
	}
}
