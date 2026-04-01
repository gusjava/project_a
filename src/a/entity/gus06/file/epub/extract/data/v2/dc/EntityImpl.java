package a.entity.gus06.file.epub.extract.data.v2.dc;

import a.framework.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20251005";}
	
	public Object t(Object obj) throws Exception
	{
		Document doc = (Document) obj;
		if(doc == null) return null;
		Map map = new HashMap();

		try {
			// dc:* (titre, créateur, etc.)
			Elements elements = doc.select("metadata > *");
			for(Element e : elements) {
				String tag = e.tagName();
				if(tag.startsWith("dc:")) {
					String key = tag.replace("dc:", "");
					String value = e.text().trim();
					if(!key.isEmpty() && !value.isEmpty()) map.put(key, value);
				}
			}
		}
		catch(Exception e)
		{throw new Exception("Failed to extract dc from Document", e);}
		return map;
	}
}