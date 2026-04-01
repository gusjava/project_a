package a.entity.gus06.file.mime.tika.mimetoextension;

import a.framework.*;
import org.apache.tika.mime.MimeTypes;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180410";}
	
	
	public Object t(Object obj) throws Exception
	{
		String mime = (String) obj;
		String result = MimeTypes.getDefaultMimeTypes().forName(mime).getExtension();
		if(result.startsWith(".")) return result.substring(1);
		return result;
	}
}
