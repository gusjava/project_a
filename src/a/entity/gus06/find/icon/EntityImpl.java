package a.entity.gus06.find.icon;

import a.framework.*;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.io.File;
import java.awt.Image;
import java.awt.image.RenderedImage;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140729";}


	private Service stringToIcon;
	private Service imageToIcon;
	private Service renderedImageToIcon;
	private Service readFile;

	public EntityImpl() throws Exception
	{
		stringToIcon = Outside.service(this,"gus06.convert.stringtoicon");
		imageToIcon = Outside.service(this,"gus06.convert.imagetoicon");
		renderedImageToIcon = Outside.service(this,"gus06.convert.renderedimagetoicon");
		readFile = Outside.service(this,"gus06.file.read.gif.asicon");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		if(obj instanceof Icon) return obj;
		if(obj instanceof File) return fileToIcon((File) obj);
		if(obj instanceof String) return stringToIcon((String) obj);
		if(obj instanceof Image) return imageToIcon((Image) obj);
		if(obj instanceof RenderedImage) return renderedImageToIcon((RenderedImage) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private Icon fileToIcon(File file) throws Exception
	{return (Icon) readFile.t(file);}
	
	
	private Icon stringToIcon(String id) throws Exception
	{return (Icon) stringToIcon.t(id);}
	
	
	private Icon imageToIcon(Image img) throws Exception
	{return (Icon) imageToIcon.t(img);}
	
	
	private Icon renderedImageToIcon(RenderedImage img) throws Exception
	{return (Icon) renderedImageToIcon.t(img);}
}
