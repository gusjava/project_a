package a.entity.gus06.sys.expression1.apply.op._tolines_trim;

import a.framework.*;
import java.io.File;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170131";}


	private Service read;
	private Service split;
	private Service split2;
	
	public EntityImpl() throws Exception
	{
		read = Outside.service(this,"gus06.file.read.string.autodetect");
		split = Outside.service(this,"gus06.string.split.lines1.list.trimed");
		split2 = Outside.service(this,"gus06.string.split.lines1.list2.trimed");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof String) return split.t(obj);
		if(obj instanceof File) return split.t(read.t(obj));
		if(obj instanceof List) return split2.t(obj);
		if(obj instanceof Object[]) return split2.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}