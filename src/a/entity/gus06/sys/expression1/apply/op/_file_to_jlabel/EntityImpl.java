package a.entity.gus06.sys.expression1.apply.op._file_to_jlabel;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170211";}


	private Service build;
	
	public EntityImpl() throws Exception
	{
		build = Outside.service(this,"gus06.swing.label.build.filelabel");
	}



	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof File) return build.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
}
