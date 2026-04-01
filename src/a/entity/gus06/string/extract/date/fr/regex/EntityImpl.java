package a.entity.gus06.string.extract.date.fr.regex;

import java.util.regex.Pattern;
import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20190210";}
	
	
	// regex 1
	
	public static final String REGEX_99 = "[0-9]{2}";
	public static final String REGEX_YEAR1 = "[12][0-9]{3}";
	public static final String REGEX_YEAR2 = "(("+REGEX_YEAR1+")|("+REGEX_99+"))";
	
	public static final String REGEX_DAY = "((0?[1-9])|(1[0-9])|(2[0-9])|(30)|(31))";
	public static final String REGEX_MONTH = "((0?[1-9])|(10)|(11)|(12))";
	
	public static final String REGEX1 = REGEX_DAY+"/"+REGEX_MONTH+"/"+REGEX_YEAR2; //jj/mm/aaaa
	
	// regex 2
	
	public static final String REGEX_MONTH_L = "(janvier|janv\\.?|jan\\.?|f�vrier|f�vr\\.?|f�v\\.?|mars|avril|avr\\.?|mai|juin|juillet|juil\\.?|ao�t|septembre|sept\\.?|octobre|oct\\.?|novembre|nov\\.?|d�cembre|d�c\\.?)";
	
	public static final String REGEX2 = REGEX_DAY+" "+REGEX_MONTH_L+" "+REGEX_YEAR2; //jj mm aaaa
	
	// regex
	
	public static final String REGEX = "("+REGEX1+")|("+REGEX2+")";

	private Pattern p;


	public EntityImpl() throws Exception
	{p = Pattern.compile(REGEX);}


	public Object g() throws Exception
	{return p;}
}