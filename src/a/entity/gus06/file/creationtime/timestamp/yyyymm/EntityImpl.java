package a.entity.gus06.file.creationtime.timestamp.yyyymm;

import a.framework.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250903";}
	
	public static final String FORMAT = "yyyyMM";
	
	private SimpleDateFormat sdf = new SimpleDateFormat(FORMAT);

	private Service findDate;

	public EntityImpl() throws Exception
	{
		findDate = Outside.service(this,"gus06.file.creationtime.date");
	}
	
	public Object t(Object obj) throws Exception
	{
		Date date = (Date) findDate.t(obj);
		return sdf.format(date);
	}
}