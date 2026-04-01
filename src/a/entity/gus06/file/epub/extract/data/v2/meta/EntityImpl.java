package a.entity.gus06.file.epub.extract.data.v2.meta;

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
			Elements metaElements = doc.select("metadata > meta");
			for(Element e : metaElements) {
				String name = e.attr("name");
				String content = e.attr("content");
				if(!name.isEmpty() && !content.isEmpty()) map.put(name, content);
			}
		}
		catch(Exception e)
		{throw new Exception("Failed to extract metadata from Document", e);}
		return map;
	}
}