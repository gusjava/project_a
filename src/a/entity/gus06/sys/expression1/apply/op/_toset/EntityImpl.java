package a.entity.gus06.sys.expression1.apply.op._toset;

import a.framework.*;
import java.util.List;
import java.util.HashSet;
import java.sql.ResultSet;
import java.util.Map;
import java.util.Collection;
import java.util.Set;
import java.io.File;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151109";}


	private Service find;
	private Service read;
	private Service rsToSet;
	private Service stringToSet;
	private Service itToSet;
	private Service gToSet;
	
	public EntityImpl() throws Exception
	{
		find = Outside.service(this,"gus06.find.set");
		read = Outside.service(this,"gus06.file.read.string.set.autodetect");
		rsToSet = Outside.service(this,"gus06.jdbc.resultset.tostringset");
		stringToSet = Outside.service(this,"gus06.set.build.from.chars");
		itToSet = Outside.service(this,"gus06.set.build.from.iterator");
		gToSet = Outside.service(this,"gus06.set.build.from.g");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof Set) return obj;
		
		if(obj instanceof String) return stringToSet.t(obj);
		
		if(obj instanceof Object[][])		return find.t(obj);
		if(obj instanceof int[][])		return find.t(obj);
		if(obj instanceof short[][])		return find.t(obj);
		if(obj instanceof long[][])		return find.t(obj);
		if(obj instanceof double[][])		return find.t(obj);
		if(obj instanceof float[][])		return find.t(obj);
		if(obj instanceof boolean[][])		return find.t(obj);
		if(obj instanceof char[][])		return find.t(obj);
		
		if(obj instanceof Object[])		return find.t(obj);
		if(obj instanceof int[])		return find.t(obj);
		if(obj instanceof short[])		return find.t(obj);
		if(obj instanceof long[])		return find.t(obj);
		if(obj instanceof double[])		return find.t(obj);
		if(obj instanceof float[])		return find.t(obj);
		if(obj instanceof boolean[])		return find.t(obj);
		if(obj instanceof char[])		return find.t(obj);
		
		if(obj instanceof List)			return new HashSet((List) obj);
		if(obj instanceof Collection)		return new HashSet((Collection) obj);
		if(obj instanceof Map)			return new HashSet(((Map) obj).keySet());
		if(obj instanceof ResultSet)		return rsToSet.t(obj);
		if(obj instanceof File)			return read.t(obj);
		if(obj instanceof Iterator)		return itToSet.t(obj);
		if(obj instanceof G)			return gToSet.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
