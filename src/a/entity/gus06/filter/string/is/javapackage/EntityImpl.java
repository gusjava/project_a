package a.entity.gus06.filter.string.is.javapackage;

import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20251204";}
	
	public static final String KEY_JAVA_PACKAGE = "java_package";


	private Service regexFromRule;
	private String regex;
	
	public EntityImpl() throws Exception
	{
		regexFromRule = Outside.service(this,"gus06.string.transform.regexp.fromrule");
		regex = (String) regexFromRule.r(KEY_JAVA_PACKAGE);
	}

	
	public boolean f(Object obj) throws Exception
	{
		String s = (String) obj;
		return s.matches(regex);
	}
}
