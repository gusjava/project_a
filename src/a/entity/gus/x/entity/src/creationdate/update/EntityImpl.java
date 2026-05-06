package a.entity.gus.x.entity.src.creationdate.update;

import java.io.File;
import a.framework.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260505";}

	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String src = (String) o[0];
		String timeStamp = toTimeStamp(o[1]);

		String creationDateRegex = "public String creationDate\\(\\s*\\)\\s*\\{\\s*return \"([0-9]{8})\";\\s*\\}";
		String creationDateCurrent = "public String creationDate() {return \"" + timeStamp + "\";}";
		return src.replaceFirst(creationDateRegex, creationDateCurrent);
	}
	
	private String toTimeStamp(Object obj) throws Exception
	{
		if(obj instanceof String)
		{
			String timeStamp = (String) obj;
			if(!timeStamp.matches("[0-9]{8}")) 
				throw new Exception("Invalid creationDate format");
			return timeStamp;
		}
		if(obj instanceof Date)
		{
			Date date = (Date) obj;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			return sdf.format(date);
		}
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
