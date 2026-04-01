package a.entity.gus06.sys.xhtmlparser1.analyze1;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170131";}


	public static final String K_TAGPOS = "tagpos";
	public static final String K_TEXTSTART = "textstart";
	public static final String K_TEXTEND = "textend";
	
	

	private Service buildTagText;
	private Service buildTagElement;


	public EntityImpl() throws Exception
	{
		buildTagText = Outside.service(this,"gus06.sys.xhtmlparser1.analyze1.buildtag.text");
		buildTagElement = Outside.service(this,"gus06.sys.xhtmlparser1.analyze1.buildtag.element");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		List input = (List) obj;
		List output = new ArrayList();
		
		int textPos = 0;
		
		for(int i=0;i<input.size();i++)
		{
			String value = (String) input.get(i);
			int valueLength = value.length();
			
			boolean isElementTag = i%2==1;
			boolean isEmptyValue = valueLength==0;
			
			if(isElementTag && isEmptyValue) throw new Exception("Element tag found with empty value at pos="+textPos);
			if(!isElementTag && isEmptyValue) continue;
			
			T builder = isElementTag ? buildTagElement : buildTagText;
			Map tag = (Map) builder.t(value);
			output.add(tag);
			
			int textStart = textPos;
			int textEnd = textPos + valueLength;
			int tagPos = output.size()-1;
			
			if(isElementTag) textEnd += 2;
				
			tag.put(K_TEXTSTART,textStart);
			tag.put(K_TEXTEND,textEnd);
			tag.put(K_TAGPOS,tagPos);
			
			textPos = textEnd;
		}
		return output;
	}
}
