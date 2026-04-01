package a.entity.gus06.find.imagearray;

import a.framework.*;
import java.util.List;
import java.awt.Image;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180509";}


	private Service fromObjectArray;
	private Service listToObjArray;
	
	public EntityImpl() throws Exception
	{
		fromObjectArray = Outside.service(this,"gus06.convert.objarraytoimagearray.strict");
		listToObjArray = Outside.service(this,"gus06.convert.listtoobjarray");
	}

	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof Image[]) return obj;
		if(obj instanceof Image) return new Image[]{(Image) obj};
		
		if(obj instanceof Object[]) return fromObjectArray.t(obj);
		if(obj instanceof List) return fromObjectArray.t(listToObjArray.t(obj));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
