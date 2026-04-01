package a.entity.gus06.file.lastmodified.timestamp.yyyy;

import a.framework.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250903";}

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		Date date = new Date(file.lastModified());
		return sdf.format(date);
	}
}