package a.entity.gus.x.time.now.hhmmss1;

import java.text.SimpleDateFormat;
import java.util.Date;
import a.framework.*;

public class EntityImpl implements Entity, G {
	public String creationDate() {return "20240712";}

	private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	
	public Object g() throws Exception
	{return sdf.format(new Date());}
}