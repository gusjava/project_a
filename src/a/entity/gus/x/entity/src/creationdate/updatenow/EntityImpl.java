package a.entity.gus.x.entity.src.creationdate.updatenow;

import java.io.File;
import a.framework.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20251125";}

	public Object t(Object obj) throws Exception
	{
		String src = (String) obj;

		String creationDateRegex = "public String creationDate\\(\\s*\\)\\s*\\{\\s*return \"([0-9]{8})\";\\s*\\}";
		String creationDateCurrent = "public String creationDate() {return \"" + today() + "\";}";
		return src.replaceFirst(creationDateRegex, creationDateCurrent);
	}
	
	private String today()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(new Date());
	}
}
