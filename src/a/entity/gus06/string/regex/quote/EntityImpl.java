package a.entity.gus06.string.regex.quote;

import a.framework.*;
import java.util.regex.Pattern;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191224";}


	private Service quote;
	
	public EntityImpl() throws Exception
	{quote = Outside.service(this,"gus06.string.transform.regexp.quote");}
	
	public Object t(Object obj) throws Exception
	{return Pattern.compile((String) quote.t(obj));}
}
