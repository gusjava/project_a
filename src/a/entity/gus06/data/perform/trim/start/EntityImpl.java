package a.entity.gus06.data.perform.trim.start;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170201";}


	private Service performString;
	private Service performList;
	private Service performArray;
	
	public EntityImpl() throws Exception
	{
		performString = Outside.service(this,"gus.x.transform.string.trim.start");
		performList = Outside.service(this,"gus06.list.trim.start");
		performArray = Outside.service(this,"gus06.array.trim.start");
	}

	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof List) return performList.t(obj);
		if(obj instanceof Object[]) return performArray.t(obj);
		if(obj instanceof String) return performString.t(obj);
		if(obj instanceof Number) return performString.t(""+obj);
		if(obj instanceof Boolean) return performString.t(""+obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
