package a.entity.gus06.command.explore.parseargs;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140710";}

	
	public static final Pattern ARG_P = Pattern.compile("\"[^\"]*\"");
	
	
	public Object t(Object obj) throws Exception
	{return toArgs((String) obj);}
	
	
	
	private String[] toArgs(String line)
	{
		if(line==null) return null;
		
		if(!line.contains("\""))
			return line.split(" +");
		
		Matcher m = ARG_P.matcher(line);
		List l = new ArrayList();
		while(m.find()) l.add(m.group());
		
		String[] a = new String[l.size()];
		return (String[]) l.toArray(a);
	}
}
