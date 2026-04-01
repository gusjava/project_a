package a.entity.gus06.sys.expression1.apply.op._mimicdir_forfiles_all;

import a.framework.*;
import java.io.File;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180328";}


	private Service perform;
	private Service findArray;
	
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.dir.perform.mimic.searchfiles.all");
		findArray = Outside.service(this,"gus06.find.filearray");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof File[]) return perform.t(obj);
		if(obj instanceof File) return perform.t(findArray.t(obj));
		if(obj instanceof List) return perform.t(findArray.t(obj));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
