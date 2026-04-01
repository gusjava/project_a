package a.entity.gus06.string.extract.date.en.regex;

import java.util.regex.Pattern;
import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20240302";}
	
	// regex
	
	public static final String REGEX_99 = "[0-9]{2}";
	public static final String REGEX_YEAR = "[12][0-9]{3}";
	public static final String REGEX_YEAR2 = "(("+REGEX_YEAR+")|("+REGEX_99+"))";
	
	public static final String REGEX_DAY = "((0?[1-9])|(1[0-9])|(2[0-9])|(30)|(31))";
	public static final String REGEX_MONTH = "((0?[1-9])|(10)|(11)|(12))";
	
	public static final String REGEX = REGEX_YEAR2+"-"+REGEX_MONTH+"-"+REGEX_DAY; //aaaa-mm-jj
	

	private Pattern p;


	public EntityImpl() throws Exception
	{p = Pattern.compile(REGEX);}


	public Object g() throws Exception
	{return p;}
}