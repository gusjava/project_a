package a.entity.gus06.sys.expression1.apply.op._to_g;

import a.framework.*;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191031";}


	private Service itToG;
	private Service listToG;
	private Service setToG;
	
	public EntityImpl() throws Exception
	{
		itToG = Outside.service(this,"gus06.convert.iteratortog");
		listToG = Outside.service(this,"gus06.list.build.popper");
		setToG = Outside.service(this,"gus06.set.build.popper");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof G) return obj;
		if(obj instanceof Iterator) return itToG.t(obj);
		if(obj instanceof List) return listToG.t(obj);
		if(obj instanceof Set) return setToG.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
