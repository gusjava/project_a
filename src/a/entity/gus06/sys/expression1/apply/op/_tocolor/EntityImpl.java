package a.entity.gus06.sys.expression1.apply.op._tocolor;

import a.framework.*;
import java.awt.Color;
import java.util.List;
import java.awt.image.BufferedImage;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160505";}


	private Service findColor;
	private Service listToArray;
	private Service imageToColor;
	
	public EntityImpl() throws Exception
	{
		findColor = Outside.service(this,"gus06.find.color");
		listToArray = Outside.service(this,"gus06.convert.listtointarray.strict");
		imageToColor = Outside.service(this,"gus06.awt.bufferedimage.color.avg.rgb");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof Color) return findColor.t(obj);
		if(obj instanceof Integer) return findColor.t(obj);
		if(obj instanceof String) return findColor.t(obj);
		if(obj instanceof int[]) return findColor.t(obj);
		if(obj instanceof float[]) return findColor.t(obj);
		if(obj instanceof List) return findColor.t(toArray(obj));
		if(obj instanceof BufferedImage) return imageToColor.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private Object toArray(Object obj) throws Exception
	{
		Object r = listToArray.t(obj);
		if(r==null) throw new Exception("Invalid list: "+obj);
		return r;
	}
}
