package a.entity.gus06.file.epub.extract.fulltext.isbn;

import a.framework.*;
import java.io.File;
import java.util.Map;
import java.util.List;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20251123";}

	private Service extractData;
	private Service isbnExtractor;

	public EntityImpl() throws Exception
	{
		extractData = Outside.service(this,"gus06.file.epub.extract.data.v2");
		isbnExtractor = Outside.service(this,"gus06.string.transform.regexp.extract.isbn");
	}
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		Map data = (Map) extractData.t(file);
		List chapters = (List) data.get("chapters");
		
		for(int i=0;i<chapters.size();i++)
		{
			Map chapter = (Map) chapters.get(i);
			String content = (String) chapter.get("content");
			String isbn = (String) isbnExtractor.t(content);
			if(isbn!=null) return isbn;
		}
		
		return null;
	}
}
