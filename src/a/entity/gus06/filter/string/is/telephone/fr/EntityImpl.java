package a.entity.gus06.filter.string.is.telephone.fr;

import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20160611";}
	
	public static final String KEY_TEL = "tel";


	private Service regexFromRule;
	private String regex;
	
	public EntityImpl() throws Exception
	{
		regexFromRule = Outside.service(this,"gus06.string.transform.regexp.fromrule");
		regex = (String) regexFromRule.r(KEY_TEL);
	}

	
	public boolean f(Object obj) throws Exception
	{
		String s = (String) obj;
		return s.matches(regex);
	}
}
