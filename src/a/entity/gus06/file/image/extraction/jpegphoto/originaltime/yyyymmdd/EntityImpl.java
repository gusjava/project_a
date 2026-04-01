package a.entity.gus06.file.image.extraction.jpegphoto.originaltime.yyyymmdd;

import a.framework.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180218";}

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	private Service extract;

	public EntityImpl() throws Exception
	{
		extract = Outside.service(this,"gus06.file.image.extraction.jpegphoto.originaltime");
	}
	
	public Object t(Object obj) throws Exception
	{
		Date date = (Date) extract.t(obj);
		return sdf.format(date);
	}
}
