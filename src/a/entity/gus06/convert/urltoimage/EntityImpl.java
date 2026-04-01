package a.entity.gus06.convert.urltoimage;

import a.framework.*;
import java.net.URL;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140909";}


	private Service urlToFile;
	private Service readImage;
	private Service readIco;


	public EntityImpl() throws Exception
	{
		urlToFile = Outside.service(this,"gus06.convert.urltofile");
		readImage = Outside.service(this,"gus06.file.read.image.imageio");
		readIco = Outside.service(this,"gus06.file.read.ico.asimage");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		URL url = (URL) obj;
		
		if(url.getFile().toLowerCase().endsWith(".ico"))
			return readIco.t(urlToFile.t(obj));
		return readImage.t(urlToFile.t(obj));
	}
}
