package a.entity.gus06.find.image;

import a.framework.*;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import javax.swing.Icon;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180331";}


	private Service renderedToImage;
	private Service baToImage;
	private Service iconToImage;
	private Service fileToImage;

	public EntityImpl() throws Exception
	{
		renderedToImage = Outside.service(this,"gus06.convert.renderedimagetobufferedimage");
		baToImage = Outside.service(this,"gus06.convert.bytearraytobufferedimage");
		iconToImage = Outside.service(this,"gus06.convert.icontoimage");
		fileToImage = Outside.service(this,"gus06.file.read.image.imageio");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		if(obj instanceof Image) return obj;
		if(obj instanceof Icon) return iconToImage.t(obj);
		if(obj instanceof byte[]) return baToImage.t(obj);
		if(obj instanceof RenderedImage) return renderedToImage.t(obj);
		if(obj instanceof File) return fileToImage.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
