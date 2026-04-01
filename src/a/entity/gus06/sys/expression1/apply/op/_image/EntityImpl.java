package a.entity.gus06.sys.expression1.apply.op._image;

import a.framework.*;
import java.io.File;
import java.net.URL;
import java.awt.Image;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151113";}


	private Service findImage;
	private Service mergeImages;
	
	public EntityImpl() throws Exception
	{
		findImage = Outside.service(this,"gus06.image.find");
		mergeImages = Outside.service(this,"gus06.awt.bufferedimage.merge.grid");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof File)		return findImage.t(obj);
		if(obj instanceof URL)		return findImage.t(obj);
		if(obj instanceof byte[])	return findImage.t(obj);
		if(obj instanceof Image[][])	return mergeImages.t(obj);
		if(obj instanceof Image[])	return mergeImages.t(new Image[][]{(Image[]) obj});
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
