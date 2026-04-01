package a.entity.gus06.sys.dirsearch1.textextractor.build1.contains_i;

import a.framework.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191226";}


	private Service buildPattern;
	
	public EntityImpl() throws Exception
	{buildPattern = Outside.service(this,"gus06.string.regex.quote");}
	
	public Object t(Object obj) throws Exception
	{return new Extractor((String) obj);}
	
	private class Extractor implements T
	{
		private Pattern p;
		
		public Extractor(String search) throws Exception
		{p = (Pattern) buildPattern.t(search.toLowerCase());}
		
		public Object t(Object obj) throws Exception
		{
			String text = ((String) obj).toLowerCase();
			Matcher m = p.matcher(text);
			
			List list = new ArrayList();
			while(m.find())
			list.add(new int[]{m.start(),m.end()});
			
			return list.isEmpty() ? null : list;
		}
	}
}