package a.entity.gus06.file.epub.extract.data.v2.item;

import a.framework.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
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
			for (Element e : doc.select("manifest > item"))
			{
				String id = e.attr("id");
				String href = e.attr("href");
				if (!id.isEmpty() && !href.isEmpty()) 
				map.put(id, href);
			}
		}
		catch(Exception e)
		{throw new Exception("Failed to extract items from Document", e);}
		return map;
	}
}