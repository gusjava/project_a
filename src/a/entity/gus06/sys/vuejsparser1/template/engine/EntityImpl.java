package a.entity.gus06.sys.vuejsparser1.template.engine;

import a.framework.*;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20260104";}

	private Service findString;
	private Service parseTemplate;

	public EntityImpl() throws Exception
	{
		findString = Outside.service(this,"gus06.find.string");
		parseTemplate = Outside.service(this,"gus06.sys.vuejsparser1.template.parser");
	}
	
	public Object t(Object obj) throws Exception
	{
		String content = (String) findString.t(obj);
		Map output = new HashMap();
		
		// Regex pour <template> (greedy)
		Pattern templatePattern = Pattern.compile("(?is)<template\\b[^>]*>(.*)</template>");
		Matcher templateMatcher = templatePattern.matcher(content);
		if (templateMatcher.find())
		{
			String template = templateMatcher.group(1).trim();
			Map templateData = (Map) parseTemplate.t(template);
			
			output.put("template", template);
			output.put("templateData", templateData);
		}
		
		return output;
	}
}
