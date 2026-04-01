package a.entity.gus06.java.srccode.entity.isvalid.creationdate;

import a.framework.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class EntityImpl implements Entity, F {
	public String creationDate() {return "20251204";}

	private Service rmWhitespace;
	
	public EntityImpl() throws Exception
	{
		rmWhitespace = Outside.service(this,"gus06.string.transform.character.remove.whitespace");
	}
	
	public boolean f(Object obj) throws Exception
	{
		String body = (String) obj;
		body = (String) rmWhitespace.t(body);
		if(!body.startsWith("{return\"")) return false;
		if(!body.endsWith("\";}")) return false;
		
		String date = body.substring(8,16);
		if(!date.startsWith("2")) return false;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		sdf.setLenient(false);
		try
		{
			sdf.parse(date);
			return true;
		}
		catch(ParseException e)
		{return false;}
	}
}
