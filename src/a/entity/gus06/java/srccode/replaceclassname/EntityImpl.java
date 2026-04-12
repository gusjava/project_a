package a.entity.gus06.java.srccode.replaceclassname;

import a.framework.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class EntityImpl implements Entity, T {
	public String creationDate() { return "20251219"; }

	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length != 2) throw new Exception("Wrong data number: " + o.length);

		String src = (String) o[0];
		String newClassName = (String) o[1];

		if(newClassName == null || newClassName.isEmpty())
			throw new Exception("Invalid null or empty className");

		Pattern pClass = Pattern.compile(
			"(?m)^(\\s*(public|protected|private)?\\s*)"
			+ "(class|interface|enum|record)\\s+"
			+ "([A-Za-z_][A-Za-z0-9_]*)\\b"
		);
		Matcher mClass = pClass.matcher(src);
		if(!mClass.find()) throw new Exception("No class/interface/enum/record declaration found");

		String prefix = mClass.group(1);
		String typeKeyword = mClass.group(3);
		String oldClassName = mClass.group(4);	

		String replacement = prefix + typeKeyword + " " + newClassName;
		src = mClass.replaceFirst(replacement);
		
		Pattern pCtor = Pattern.compile(
			"(?m)^(\\s*(public|protected|private)\\s+)"
			+ Pattern.quote(oldClassName)
			+ "\\s*\\("
		);

		Matcher mCtor = pCtor.matcher(src);
		src = mCtor.replaceAll("$1" + newClassName + "(");
		
		return src;
	}
}