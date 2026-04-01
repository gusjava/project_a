package a.entity.gus06.java.srccode.entity.parser.creationdate;

import a.framework.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20251204";}

	private Service rmWhitespace;
	
	public EntityImpl() throws Exception
	{
		rmWhitespace = Outside.service(this,"gus06.string.transform.character.remove.whitespace");
	}
	
	public Object t(Object obj) throws Exception
	{
		String body = (String) obj;
		body = (String) rmWhitespace.t(body);
		if(!body.startsWith("{return\"")) return null;
		if(!body.endsWith("\";}")) return null;
		
		String date = body.substring(8,16);
		if(!date.startsWith("2")) return null;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		sdf.setLenient(false);
		try
		{
			sdf.parse(date);
			return date;
		}
		catch(ParseException e)
		{return null;}
	}
}
