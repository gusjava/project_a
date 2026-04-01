package a.entity.gus06.sys.expression1.apply.op._entitysrc_infomap;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180410";}
	

	private Service fromFile;
	private Service fromCode;


	public EntityImpl() throws Exception
	{
		fromFile = Outside.service(this,"gus06.java.srcfile.extract.entity.infomap1");
		fromCode = Outside.service(this,"gus06.java.srccode.extract.entity.infomap1");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof String) return fromCode.t(obj);
		if(obj instanceof File) return fromFile.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
