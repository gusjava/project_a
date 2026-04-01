package a.entity.gus06.file.epub.extract.data.v2.reference;

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
			for (Element e : doc.select("guide > reference"))
			{
				String type = e.attr("type");
				String href = e.attr("href");
				if (!type.isEmpty() && !href.isEmpty()) 
				map.put(type, href);
			}
		}
		catch(Exception e)
		{throw new Exception("Failed to extract reference from Document", e);}
		return map;
	}
}