package a.entity.gus06.data.string.replace4;

import a.framework.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220501";}


	private Service buildPattern;

	public EntityImpl() throws Exception
	{
		buildPattern = Outside.service(this,"gus06.string.extract.extract1.pattern");
	}


	public Object t(Object obj) throws Exception
	{
		String[] o = (String[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		String line = o[0];
		String s1 = o[1];
		String s2 = o[2];
		
		return replace(line,s1,s2);
	}
	
	private String replace(String line, String s1, String s2) throws Exception
	{
		String pattern = (String) buildPattern.t(s1);
		Pattern p = Pattern.compile(pattern,Pattern.DOTALL);
		Matcher m = p.matcher(line);
		
		StringBuffer b = new StringBuffer();
		String repl = s1.replace("***",s2).replace("**",s2).replace("*",s2);
		while(m.find()) m.appendReplacement(b,repl);
		m.appendTail(b);
		return b.toString();
	}
}