package a.entity.gus06.string.regex.quote.i;

import a.framework.*;
import java.util.regex.Pattern;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20210630";}


	private Service quote;
	
	public EntityImpl() throws Exception
	{quote = Outside.service(this,"gus06.string.transform.regexp.quote");}
	
	public Object t(Object obj) throws Exception
	{return Pattern.compile("(?si)"+quote.t(obj));}
}