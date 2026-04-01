package a.entity.gus06.icon.write.tofile;

import a.framework.*;
import java.io.File;
import javax.swing.Icon;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.awt.Image;
import java.net.URL;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20201205";}

	private Service copyFile;
	private Service fileToIcon;
	private Service writeImage;
	private Service urlToImage;
	private Service extractFavIcon;
	private Service resize16x16;

	public EntityImpl() throws Exception
	{
		copyFile = Outside.service(this,"gus06.file.op.copy");
		fileToIcon = Outside.service(this,"gus06.file.icon.os.lnk");
		writeImage = Outside.service(this,"gus06.file.write.image.gif");
		urlToImage = Outside.service(this,"gus06.convert.urltoimage");
		extractFavIcon = Outside.service(this,"gus06.url.extractresources.favicon");
		resize16x16 = Outside.service(this,"gus06.awt.bufferedimage.resize.s16x16");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		Object data = o[1];
		
		if(data instanceof File) writeFromFile(file,(File) data);
		if(data instanceof URL) writeFromURL(file,(URL) data);
		else if(data instanceof BufferedImage) writeFromImg(file,data);
		else if(data instanceof RenderedImage) writeFromImg(file,data);
		else if(data instanceof Image) writeFromImg(file,data);
		else if(data instanceof Icon) writeFromImg(file,data);
		else if(data instanceof byte[]) writeFromImg(file,data);
	}
	
	
	
	private void writeFromFile(File f, File data) throws Exception
	{
		if(!data.exists()) return;
		
		if(data.isFile())
		{
			String name = data.getName().toLowerCase();
			if(name.endsWith(".gif") || name.endsWith(".png"))
			{
				copyFile.p(new File[]{data,f});
				return;
			}
		}
		
		Icon icon = (Icon) fileToIcon.t(data);
		writeImage.p(new Object[]{f,icon});
	}
	
	
	
	private void writeFromURL(File f, URL data) throws Exception
	{
		try
		{
			Object image = urlToImage.t(data);
			if(image!=null)
			{
				writeFromImg(f,image);
				return;
			}
		}
		catch(Exception e){}
		
		URL favURL = (URL) extractFavIcon.t(data);
		if(favURL==null) throw new Exception("Fav icon URL not found for url: "+data);
		
		Object image = urlToImage.t(favURL);
		if(image==null) throw new Exception("Icon not found for FAV url: "+favURL);
		
		writeFromImg(f,image);
	}
	
	
	
	private void writeFromImg(File f, Object data) throws Exception
	{
		if(data==null) throw new Exception("Attempt to save null image");
		data = resize16x16.t(data);
		writeImage.p(new Object[]{f,data});
	}
}