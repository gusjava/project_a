package a.entity.gus06.java.srccode.rename.classname;

import a.framework.*;
import java.util.regex.Matcher;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190717";}


	private Service extractName;
	private Service quote;


	public EntityImpl() throws Exception
	{
		extractName = Outside.service(this,"gus06.java.srccode.extract.classname");
		quote = Outside.service(this,"gus06.string.transform.regexp.quote");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String[] o = (String[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String src = o[0];
		String newName = o[1];
		
		String oldName = (String) extractName.t(src);
		
		String oldName1 = (String) quote.t(oldName);
		String newName1 = Matcher.quoteReplacement(newName);
		
		String[] lines = src.split("\n",-1);
		StringBuffer b = new StringBuffer();
		
		for(String line : lines)
		{
			String line_ = line.trim();
			if(line_.startsWith("class "+oldName))
				line = line.replaceFirst(oldName1,newName1);
			else if(line_.startsWith("interface "+oldName))
				line = line.replaceFirst(oldName1,newName1);
			else if(line_.startsWith("enumeration "+oldName))
				line = line.replaceFirst(oldName1,newName1);
			else if(line_.startsWith("public "+oldName+"("))
				line = line.replaceFirst(oldName1,newName1);
			else if(line_.startsWith("private "+oldName+"("))
				line = line.replaceFirst(oldName1,newName1);
			else if(line_.startsWith("protected "+oldName+"("))
				line = line.replaceFirst(oldName1,newName1);
		}
		
		return b.toString();
	}
}
