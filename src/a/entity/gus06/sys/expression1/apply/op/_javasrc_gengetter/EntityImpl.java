package a.entity.gus06.sys.expression1.apply.op._javasrc_gengetter;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190325";}


	private Service perform;
	private Service find;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.java.srccode.generate.getter");
		find = Outside.service(this,"gus06.find.stringarray");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof String[]) return perform.t(obj);
		if(obj instanceof List) return perform.t(find.t(obj));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}