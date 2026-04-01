package a.entity.gus06.file.image.extraction.jpegphoto.originaltime;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180218";}

	public static final String KEY_ORIGINALDATE = "Exif.Date/Time Original";

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
	
	private Service extract;
	
	public EntityImpl() throws Exception
	{
		extract = Outside.service(this,"gus06.image.metadata.jpeg.extraction1");
	}
	
	public Object t(Object obj) throws Exception
	{
		Map m = (Map) extract.t(obj);
		String info = (String) m.get(KEY_ORIGINALDATE);
		return sdf.parse(info);
	}
}
