package a.entity.gus06.image.metadata.jpeg.extraction1.exifreading.essai3;

import java.util.HashMap;
import java.util.Iterator;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180218";}


	public EntityImpl() throws Exception
	{
	}


	public Object t(Object obj) throws Exception
	{
		byte[] data = (byte[]) obj;
		
		Metadata meta = new Metadata();
		new ExifReader(data).extract(meta);
		
		HashMap map = new HashMap();
		Iterator it = meta.getDirectoryIterator();
		while(it.hasNext())
		{
			Directory dir = (Directory)it.next();
			String dirName = dir.getName();
			
			Iterator tags = dir.getTagIterator();
			while (tags.hasNext()) {
				Tag tag = (Tag)tags.next();
				String name = tag.getTagName();
				String desc = tag.getDescription();
				map.put(dirName+"."+name,desc);
			}
		}
		return map;
	}
}
