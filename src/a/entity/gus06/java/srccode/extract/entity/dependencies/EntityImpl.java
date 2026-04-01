package a.entity.gus06.java.srccode.extract.entity.dependencies;

import a.framework.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Set;
import java.util.HashSet;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150311";}
	
	public static final String PART = "[a-z_][a-z0-9_]*";
	public static final String PSEUDO = "[a-z][a-z][a-z][a-z0-9]*";
	public static final String NAME = "("+PSEUDO+")(\\."+PART+")(\\."+PART+")+";
	
	public static final Pattern P = Pattern.compile(NAME);

	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		return extract((String) obj);
	}
	
	
	private Set extract(String s) throws Exception
	{
		Set set = new HashSet();
		Matcher m = P.matcher(s);
		while(m.find()) set.add(m.group());
		return set;
	}
}