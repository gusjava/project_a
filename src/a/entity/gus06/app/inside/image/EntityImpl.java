package a.entity.gus06.app.inside.image;

import java.net.URL;

import javax.swing.ImageIcon;

import a.framework.*;
import javax.imageio.ImageIO;

public class EntityImpl implements Entity, T, R {

	public String creationDate() {return "20200516";}


	private Service inside;
	
	public EntityImpl() throws Exception
	{inside = Outside.service(this,"inside");}
	
	
	public Object t(Object obj) throws Exception
	{
		String imageId = (String) obj;
		
		{
			String path = "image/"+imageId+".gif";
			URL url = (URL) inside.t("url."+path);
			if(url!=null) return ImageIO.read(url);
		}
		{
			String path = "image/"+imageId+".png";
			URL url = (URL) inside.t("url."+path);
			if(url!=null) return ImageIO.read(url);
		}
		{
			String path = "image/"+imageId+".jpg";
			URL url = (URL) inside.t("url."+path);
			if(url!=null) return ImageIO.read(url);
		}
		{
			String path = "image/"+imageId+".jpeg";
			URL url = (URL) inside.t("url."+path);
			if(url!=null) return ImageIO.read(url);
		}
		{
			String path = "image/"+imageId+".bmp";
			URL url = (URL) inside.t("url."+path);
			if(url!=null) return ImageIO.read(url);
		}
		
		return null;
	}
	
	public Object r(String key) throws Exception
	{return t(key);}
}
