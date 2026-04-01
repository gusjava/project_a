package a.entity.gus06.string.regex.email;

import java.util.regex.Pattern;

import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20190519";}
	
	//public static final String REGEX = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}";
	public static final String REGEX = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}\\@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+";


	private Pattern p;

	public EntityImpl() throws Exception
	{p = Pattern.compile(REGEX,Pattern.DOTALL);}

	public Object g() throws Exception
	{return p;}
}
