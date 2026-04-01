package a.entity.gus06.find.stringarray;

import a.framework.*;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140910";}


	private Service fromList;
	private Service fromArray;
	private Service fromDoubleArray;
	private Service fromLongArray;
	private Service fromIntArray;
	private Service fromBooleanArray;
	
	public EntityImpl() throws Exception
	{
		fromList = Outside.service(this,"gus06.convert.listtostringarray");
		fromArray = Outside.service(this,"gus06.convert.objarraytostringarray");
		fromDoubleArray = Outside.service(this,"gus06.convert.doublearraytostringarray");
		fromLongArray = Outside.service(this,"gus06.convert.longarraytostringarray");
		fromIntArray = Outside.service(this,"gus06.convert.intarraytostringarray");
		fromBooleanArray = Outside.service(this,"gus06.convert.booleanarraytostringarray");
	}

	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		if(obj instanceof String[]) return obj;
		if(obj instanceof Object[]) return fromArray.t(obj);
		if(obj instanceof List) return fromList.t(obj);
		if(obj instanceof Set) return fromList.t(new ArrayList((Set) obj));
		
		if(obj instanceof double[]) return fromDoubleArray.t(obj);
		if(obj instanceof long[]) return fromLongArray.t(obj);
		if(obj instanceof int[]) return fromIntArray.t(obj);
		if(obj instanceof boolean[]) return fromBooleanArray.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
