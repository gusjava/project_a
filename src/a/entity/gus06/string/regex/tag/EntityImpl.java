package a.entity.gus06.string.regex.tag;

import java.util.regex.Pattern;

import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20190519";}
	
	public static final String REGEX = "<([^>])+>";


	private Pattern p;

	public EntityImpl() throws Exception
	{p = Pattern.compile(REGEX,Pattern.DOTALL);}

	public Object g() throws Exception
	{return p;}
}
