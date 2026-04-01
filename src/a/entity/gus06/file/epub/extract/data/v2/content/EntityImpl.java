package a.entity.gus06.file.epub.extract.data.v2.content;

import a.framework.*;
import java.util.zip.ZipFile;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.io.InputStream;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20251005";}

	private Service isToString;

	public EntityImpl() throws Exception
	{
		isToString = Outside.service(this,"gus06.io.transfer.tostring.utf8");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if (o.length != 5) throw new Exception("Wrong data number: " + o.length);
		
		ZipFile zipFile = (ZipFile) o[0];
		Map entryMap = (Map) o[1];
		Document opfDoc = (Document) o[2];
		Map map_item = (Map) o[3];
		String root = (String) o[4];
		
		List chapters = new ArrayList();
		
		try 
		{
			Elements spineElements = opfDoc.select("spine > itemref");
			for (Element e : spineElements)
			{
				String idref = e.attr("idref");
				if (idref == null || idref.isEmpty()) continue;
				
				String href = (String) map_item.get(idref);
				if (href == null) continue;
				
				String fullPath = root + href;
				String content = readEntryAsString(zipFile, entryMap, fullPath);
				
				// Parser le XHTML pour extraire un titre simple
				Document chapterDoc = Jsoup.parse(content);
				String title = extractTitle(chapterDoc);
				
				Map mapChapter = new HashMap();
				mapChapter.put("idref", idref);
				mapChapter.put("href", href);
				mapChapter.put("fullPath", fullPath);
				mapChapter.put("title", title);
				mapChapter.put("content", content);
				
				chapters.add(mapChapter);
			}
		}
		catch (Exception e)
		{throw new Exception("Failed to extract content chapters", e);}
		
		return chapters;
	}
	
	
	private String readEntryAsString(ZipFile zipFile, Map entryMap, String name) throws Exception 
	{
		try
		{
			ZipEntry entry = (ZipEntry) entryMap.get(name);
			if (entry == null) return null;
			InputStream is = zipFile.getInputStream(entry);
			return (String) isToString.t(is);
		}
		catch (Exception e)
		{return null;}
	}
	
	private String extractTitle(Document doc)
	{
		Elements h1List = doc.select("h1");
		Element h1 = (h1List.size() > 0 ? h1List.get(0) : null);
		if (h1 != null) return h1.text().trim();
		
		Elements titleList = doc.select("title");
		Element titleTag = (titleList.size() > 0 ? titleList.get(0) : null);
		if (titleTag != null) return titleTag.text().trim();
		
		return null; // pas de titre trouvé
	}
}