package a.entity.gus06.sys.vuejsparser1.script.engine;

import a.framework.*;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20260104";}

	private Service findString;
	private Service parseScript;

	public EntityImpl() throws Exception
	{
		findString = Outside.service(this,"gus06.find.string");
		parseScript = Outside.service(this,"gus06.sys.vuejsparser1.script.parser");
	}
	
	public Object t(Object obj) throws Exception
	{
		String content = (String) findString.t(obj);
		Map output = new HashMap();
		
		// Regex pour <script> (non-greedy)
		Pattern scriptPattern = Pattern.compile("(?is)<script\\b[^>]*>(.*?)</script>");
		Matcher scriptMatcher = scriptPattern.matcher(content);
		if (scriptMatcher.find())
		{
			String script = scriptMatcher.group(1).trim();
			Map scriptData = (Map) parseScript.t(script);
			
			output.put("script", script);
			output.put("scriptData", scriptData);
		}
		
		return output;
	}
}
