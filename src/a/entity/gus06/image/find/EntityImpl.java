package a.entity.gus06.image.find;

import a.framework.*;
import java.awt.Image;
import java.awt.image.RenderedImage;
import java.io.File;
import java.net.URL;
import javax.swing.Icon;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180306";}


	private Service readImage;
	private Service urlToImage;
	private Service baToImage;
	private Service iconToImage;
	
	public EntityImpl() throws Exception
	{
		readImage = Outside.service(this,"gus06.file.read.image.generic");
		urlToImage = Outside.service(this,"gus06.convert.urltoimage");
		baToImage = Outside.service(this,"gus06.convert.bytearraytobufferedimage");
		iconToImage = Outside.service(this,"gus06.convert.icontoimage");
	}

	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof Image)		return obj;
		if(obj instanceof RenderedImage)	return obj;
		if(obj instanceof byte[])		return baToImage.t(obj);
		if(obj instanceof URL)			return urlToImage.t(obj);
		if(obj instanceof File)			return readImage.t(obj);
		if(obj instanceof Icon)			return iconToImage.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
