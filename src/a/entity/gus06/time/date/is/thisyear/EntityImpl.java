package a.entity.gus06.time.date.is.thisyear;

import a.framework.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20160801";}
	

	
	public boolean f(Object obj) throws Exception
	{
		Date date = (Date) obj;
		
		String y = yyyy(date);
		String y0 = yyyy(new Date());
		
		return y.equals(y0);
	}
	
	
	
	private SimpleDateFormat yyyy = new SimpleDateFormat("yyyy");
	
	private String yyyy(Date date)
	{return yyyy.format(date);}
}
