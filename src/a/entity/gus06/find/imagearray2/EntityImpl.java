package a.entity.gus06.find.imagearray2;

import a.framework.*;
import java.util.List;
import java.awt.Image;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180508";}


	private Service fromObjectArray2;
	private Service listToObjArray2;
	
	public EntityImpl() throws Exception
	{
		fromObjectArray2 = Outside.service(this,"gus06.convert.objarray2toimagearray2.strict");
		listToObjArray2 = Outside.service(this,"gus06.convert.listtoobjarray2");
	}

	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof Image[][]) return obj;
		if(obj instanceof Image[]) return new Image[][]{(Image[]) obj};
		if(obj instanceof Image) return new Image[][]{{(Image) obj}};
		
		if(obj instanceof Object[][]) return fromObjectArray2.t(obj);
		if(obj instanceof List) return fromObjectArray2.t(listToObjArray2.t(obj));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
