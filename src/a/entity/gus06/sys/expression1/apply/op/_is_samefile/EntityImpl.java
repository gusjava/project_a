package a.entity.gus06.sys.expression1.apply.op._is_samefile;

import a.framework.*;
import java.util.List;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220922";}


	private Service perform;
	private Service findFileArray;
	
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.filter.filearray.same");
		findFileArray = Outside.service(this,"gus06.find.filearray");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof File[]) return perform.f(obj);
		if(obj instanceof Object[]) return perform.f(findFileArray.t(obj));
		if(obj instanceof List) return perform.f(findFileArray.t(obj));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}