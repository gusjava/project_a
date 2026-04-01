package a.entity.gus06.sys.dirsearch1.textextractor.build1.contains_n;

import a.framework.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191226";}


	private Service buildPattern;
	private Service normalize;
	
	public EntityImpl() throws Exception
	{
		buildPattern = Outside.service(this,"gus06.string.regex.quote");
		normalize = Outside.service(this,"gus06.string.transform.normalize.diacritics.lower");
	}
	
	public Object t(Object obj) throws Exception
	{return new Extractor((String) obj);}
	
	private class Extractor implements T
	{
		private Pattern p;
		
		public Extractor(String search) throws Exception
		{p = (Pattern) buildPattern.t(normalize.t(search));}
		
		public Object t(Object obj) throws Exception
		{
			String text = (String) normalize.t(obj);
			Matcher m = p.matcher(text);
			
			List list = new ArrayList();
			while(m.find())
			list.add(new int[]{m.start(),m.end()});
			
			return list.isEmpty() ? null : list;
		}
	}
}