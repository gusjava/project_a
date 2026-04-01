package a.entity.gus06.time.date.format.datetime.en.format2;

import a.framework.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201116";}
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm");

	public Object t(Object obj) throws Exception
	{
		Date date = (Date) obj;
		if(date==null) return "";
		return sdf.format(date);
	}
}