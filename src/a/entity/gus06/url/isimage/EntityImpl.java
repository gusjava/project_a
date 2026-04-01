package a.entity.gus06.url.isimage;

import a.framework.*;
import java.net.URL;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20150214";}


	public boolean f(Object obj) throws Exception
	{return isImage((URL) obj);}
	
	
	private boolean isImage(URL url)
	{
		String n = url.getFile().toLowerCase();
		return n.endsWith(".jpg") || n.endsWith(".jpeg") || n.endsWith(".gif")
				|| n.endsWith(".png") || n.endsWith(".tif") || n.endsWith(".bmp");
	}
}
