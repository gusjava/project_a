package a.entity.gus06.filter.string.is.htmlcolor;

import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20220611";}
	
	public static final String KEY_HTML_COLOR = "html_color";


	private Service regexFromRule;
	private String regex;
	
	public EntityImpl() throws Exception
	{
		regexFromRule = Outside.service(this,"gus06.string.transform.regexp.fromrule");
		regex = (String) regexFromRule.r(KEY_HTML_COLOR);
	}

	
	public boolean f(Object obj) throws Exception
	{
		String s = (String) obj;
		return s.matches(regex);
	}
}