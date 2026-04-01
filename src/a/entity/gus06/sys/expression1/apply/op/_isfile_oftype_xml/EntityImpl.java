package a.entity.gus06.sys.expression1.apply.op._isfile_oftype_xml;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160806";}


	private Service isOfType;

	public EntityImpl() throws Exception
	{
		isOfType = Outside.service(this,"gus06.file.filter.mime.isoftype.application.xml");
	}


	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return Boolean.FALSE;
		if(!(obj instanceof File)) return Boolean.FALSE;
		
		return Boolean.valueOf(isOfType.f(obj));
	}
}
