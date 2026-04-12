package a.entity.gus06.file.epub.extract.fulltext;

import a.framework.*;
import java.io.File;
import java.util.Map;
import java.util.List;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20251123";}

	private Service extractData;

	public EntityImpl() throws Exception
	{
		extractData = Outside.service(this,"gus06.file.epub.extract.data.v2");
	}
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		Map data = (Map) extractData.t(file);
		List chapters = (List) data.get("chapters");
		
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<chapters.size();i++)
		{
			Map chapter = (Map) chapters.get(i);
			String title = (String) chapter.get("title");
			String content = (String) chapter.get("content");
			
			sb.append("_________________________\n");
			sb.append(title+"\n\n");
			sb.append(content+"\n");
		}
		
		return sb.toString();
	}
}
