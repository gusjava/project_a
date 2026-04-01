package a.entity.gus06.sys.linecomparator1.buildset;

import a.framework.*;
import java.util.Set;
import java.util.HashSet;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20210717";}


	private Service normalize;

	public EntityImpl() throws Exception
	{normalize = Outside.service(this,"gus06.string.transform.normalize.diacritics.lower");}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String[] lines = (String[]) o[0];
		String options = (String) o[1];
		
		Set set = new HashSet();
		for(String line : lines)
		{
			String line1 = formatLine(line,options);
			if(line1!=null) set.add(line1);
		}
		return set;
	}
	
	
	private String formatLine(String line, String options) throws Exception
	{
		if(options.contains("t")) line = line.trim();
		
		if(options.contains("e") && line.equals("")) return null;
		
		if(options.contains("n")) return (String) normalize.t(line);
		if(options.contains("i")) return line.toLowerCase();
		
		return line;
	}
}