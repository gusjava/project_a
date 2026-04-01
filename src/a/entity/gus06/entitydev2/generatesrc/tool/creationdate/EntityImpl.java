package a.entity.gus06.entitydev2.generatesrc.tool.creationdate;

import a.framework.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EntityImpl implements Entity, G {
	public String creationDate() {return "20251205";}

	public Object g() throws Exception
	{
		return "\tpublic String creationDate() {return \""+today()+"\";}\n\n";
	}
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	private String today() {return sdf.format(new Date());}
}
