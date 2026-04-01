package a.entity.gus06.string.regex.url;

import java.util.regex.Pattern;

import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20190519";}
	
	public static final String REGEX = "https?:\\/\\/[a-zA-Z0-9._%+-]+\\.[a-z]{2,4}(\\/[a-zA-Z0-9._%+@()=&#?-]+)*\\/?";


	private Pattern p;

	public EntityImpl() throws Exception
	{p = Pattern.compile(REGEX,Pattern.CASE_INSENSITIVE);}

	public Object g() throws Exception
	{return p;}
}
