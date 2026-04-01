package a.entity.gus06.time.date.yyyymmdd.point;

import java.text.SimpleDateFormat;
import java.util.Date;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220206";}


	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
	
	
	public Object t(Object obj) throws Exception
	{return sdf.format((Date) obj);}
}