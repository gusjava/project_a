package a.entity.gus06.sys.expression1.apply.op._javadir_packagemap;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170220";}


	private Service find;
	
	public EntityImpl() throws Exception
	{
		find = Outside.service(this,"gus06.sys.javaprojectviewer1.root.build.packagemap");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof File) return find.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
