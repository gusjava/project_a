package a.entity.gus06.string.transform.regexp.infer;

import a.framework.*;
import java.util.regex.Pattern;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141105";}


	public static final String KEY_QUOTE = "\'\'";
	public static final String KEY_QUOTE2 = "\"\"";
	public static final String KEY_QUOTE3 = "``";
	public static final String KEY_SQUARE = "[]";
	public static final String KEY_CURLY = "{}";
	public static final String KEY_ANGLE = "<>";
	public static final String KEY_PARENTHESIS = "()";
	public static final String KEY_EMAIL = "email";
	public static final String KEY_URL = "url";
	public static final String KEY_TEL = "tel";
	public static final String KEY_TITLED = "titled";
	public static final String KEY_DIGIT = "digit";
	public static final String KEY_NUMBER = "number";
	public static final String KEY_LOWER = "lower";
	public static final String KEY_LOWER0 = "lower0";
	public static final String KEY_LOWER1 = "lower1";
	public static final String KEY_LOWER10 = "lower10";
	public static final String KEY_UPPER = "upper";
	public static final String KEY_UPPER0 = "upper0";
	public static final String KEY_UPPER1 = "upper0";
	public static final String KEY_UPPER10 = "upper10";
	public static final String KEY_WORD = "word";
	public static final String KEY_WORD1 = "word1";
	public static final String KEY_ALPHANUM = "alphanum";
	public static final String KEY_ALPHANUM1 = "alphanum1";
	public static final String KEY_HIRAGANA = "hiragana";
	public static final String KEY_KATAKANA = "katakana";
	public static final String KEY_WHITE = "white";
	public static final String KEY_TAB = "tab";
	public static final String KEY_DATE_FR = "date_fr";
	public static final String KEY_DATE_EN = "date_en";
	public static final String KEY_DATETIME_EN = "datetime_en";
	
	
	
	

	private Service fromRule;
	private Service quote;
	
	private Map map;

	public EntityImpl() throws Exception
	{
		fromRule = Outside.service(this,"gus06.string.transform.regexp.fromrule");
		quote = Outside.service(this,"gus06.string.transform.regexp.quote");
		
		map = (Map) fromRule.g();
	}

	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		
		if(matches(s,KEY_QUOTE)) return rule(KEY_QUOTE);
		if(matches(s,KEY_QUOTE2)) return rule(KEY_QUOTE2);
		if(matches(s,KEY_QUOTE3)) return rule(KEY_QUOTE3);
		if(matches(s,KEY_SQUARE)) return rule(KEY_SQUARE);
		if(matches(s,KEY_CURLY)) return rule(KEY_CURLY);
		if(matches(s,KEY_ANGLE)) return rule(KEY_ANGLE);
		if(matches(s,KEY_PARENTHESIS)) return rule(KEY_PARENTHESIS);
		
		if(matches(s,KEY_EMAIL)) return rule(KEY_EMAIL);
		if(matches(s,KEY_URL)) return rule(KEY_URL);
		if(matches(s,KEY_TEL)) return rule(KEY_TEL);
		
		if(matches(s,KEY_DATETIME_EN)) return rule(KEY_DATETIME_EN);
		if(matches(s,KEY_DATE_FR)) return rule(KEY_DATE_FR);
		if(matches(s,KEY_DATE_EN)) return rule(KEY_DATE_EN);
		
		if(matches(s,KEY_DIGIT)) return rule(KEY_DIGIT);
		if(matches(s,KEY_NUMBER)) return rule(KEY_NUMBER);
		
		if(matches(s,KEY_LOWER)) return rule(KEY_LOWER);
		if(matches(s,KEY_LOWER0)) return rule(KEY_LOWER0);
		if(matches(s,KEY_LOWER1)) return rule(KEY_LOWER1);
		if(matches(s,KEY_LOWER10)) return rule(KEY_LOWER10);

		if(matches(s,KEY_UPPER)) return rule(KEY_UPPER);
		if(matches(s,KEY_UPPER0)) return rule(KEY_UPPER0);
		if(matches(s,KEY_UPPER1)) return rule(KEY_UPPER1);
		if(matches(s,KEY_UPPER1)) return rule(KEY_UPPER1);
		
		if(matches(s,KEY_TITLED)) return rule(KEY_TITLED);
		
		if(matches(s,KEY_WORD)) return rule(KEY_WORD);
		if(matches(s,KEY_WORD1)) return rule(KEY_WORD1);

		if(matches(s,KEY_ALPHANUM)) return rule(KEY_ALPHANUM);
		if(matches(s,KEY_ALPHANUM1)) return rule(KEY_ALPHANUM1);
		
		if(matches(s,KEY_HIRAGANA)) return rule(KEY_HIRAGANA);
		if(matches(s,KEY_KATAKANA)) return rule(KEY_KATAKANA);
		
		if(matches(s,KEY_WHITE)) return rule(KEY_WHITE);
		if(matches(s,KEY_TAB)) return rule(KEY_TAB);
		
		return quote.t(s);
	}
	
	
	
	private String rule(String key) throws Exception
	{
		if(!map.containsKey(key)) throw new Exception("Unknown rule: "+key);
		return (String) map.get(key);
	}
	
	
	private boolean matches(String s, String key) throws Exception
	{
		try{return s.matches(rule(key));}
		catch(Exception e)
		{
			String message = "match failed for key="+key;
			throw new Exception(message,e);
		}
	}
	
}
