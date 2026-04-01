package a.entity.gus06.file.lastmodified.timestamp;

import a.framework.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140915";}
	
	public static final String FORMAT = "yyyyMMdd_HHmmss";
	
	private SimpleDateFormat sdf = new SimpleDateFormat(FORMAT);
	
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		Date date = new Date(file.lastModified());
		return sdf.format(date);
	}
}
