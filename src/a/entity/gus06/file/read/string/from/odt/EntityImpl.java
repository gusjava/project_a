package a.entity.gus06.file.read.string.from.odt;

import org.jopendocument.dom.text.TextDocument;
import org.jopendocument.dom.text.Paragraph;
import org.jopendocument.dom.ODNodeDesc.Children;
import a.framework.*;
import java.io.File;
import java.util.List;
import java.io.FileInputStream;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250516";}
	
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		TextDocument doc = TextDocument.createFromFile(file);
		Children<Paragraph> paragraphs = doc.getParagraphs();
		int count = paragraphs.getCount();
		
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<count; i++)
		{
			Paragraph paragraph = paragraphs.get(i);
			sb.append("_________________________________\n");
			sb.append(paragraph.getCharacterContent()+"\n\n");
		}
		return sb.toString();
	}
}