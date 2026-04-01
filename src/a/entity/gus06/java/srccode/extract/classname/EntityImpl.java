package a.entity.gus06.java.srccode.extract.classname;

import a.framework.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160410";}
	
	public static final Pattern P = Pattern.compile("(class|interface|enum) +([A-Za-z][A-Za-z0-9_]*)",Pattern.DOTALL);


	private Service removeComments;
	
	public EntityImpl() throws Exception
	{
		removeComments = Outside.service(this,"gus06.java.srccode.remove.comments");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		String src = (String) removeComments.t(obj);
		
		Matcher m = P.matcher(src);
		if(m.find()) return m.group(2);
		
		throw new Exception("Class name extraction failed");
	}
}
