package a.entity.gus06.string.transform.regexp.extract.isbn;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201105";}

	
	public static final String N = "[0-9]";
	public static final String S = "-?";
	
	public static final String NUM10 = N+S+N+S+N+S+N+S+N+S+N+S+N+S+N+S+N+S+N;
	public static final String NUM13 = N+S+N+S+N+S+N+S+N+S+N+S+N+S+N+S+N+S+N+S+N+S+N+S+N;
	

	private Pattern p_isbn1_13 = p("(?i)isbn.*?("+NUM13+")");
	private Pattern p_isbn1_10 = p("(?i)isbn.*?("+NUM10+")");
	
	private Pattern p_isbn2_13 = p("(?i)international standard book number.*?("+NUM13+")");
	private Pattern p_isbn2_10 = p("(?i)international standard book number.*?("+NUM10+")");
	
	
	private Pattern p(String s)
	{return Pattern.compile(s,Pattern.DOTALL | Pattern.CASE_INSENSITIVE);}


	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		String text = (String) obj;
		String isbn = null;
		
		isbn = search(p_isbn1_13,text);
		if(isbn!=null) return isbn;
		
		isbn = search(p_isbn2_13,text);
		if(isbn!=null) return isbn;
		
		isbn = search(p_isbn1_10,text);
		if(isbn!=null) return isbn;
		
		isbn = search(p_isbn2_10,text);
		if(isbn!=null) return isbn;
		
		return null;
	}
	
	private String search(Pattern p, String text)
	{
		Matcher m = p.matcher(text);
		return m.find()?m.group(1):null;
	}
}