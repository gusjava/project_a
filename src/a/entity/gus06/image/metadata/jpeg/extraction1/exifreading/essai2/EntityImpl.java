package a.entity.gus06.image.metadata.jpeg.extraction1.exifreading.essai2;

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
		//printImageTags(meta);
		
		HashMap map = new HashMap();
		Iterator it = meta.getDirectoryIterator();
		while(it.hasNext())
		{
			Directory dir = (Directory)it.next();
			Iterator tags = dir.getTagIterator();
			while (tags.hasNext())
			{
				Tag tag = (Tag)tags.next();
				String name = tag.getTagName();
				String desc = tag.getDescription();
				map.put(name,desc);
			}
		}
		return map;
	}
	
	private void printImageTags(Metadata metadata)
	{
		// iterate over the exif data and print to System.out
		Iterator directories = metadata.getDirectoryIterator();
		while (directories.hasNext())
		{
			Directory directory = (Directory)directories.next();
			Iterator tags = directory.getTagIterator();
			while (tags.hasNext()) {
				Tag tag = (Tag)tags.next();
				System.out.println(tag);
			}
			if (directory.hasErrors())
			{
				Iterator errors = directory.getErrors();
				while (errors.hasNext())
				{
					System.out.println("ERROR: " + errors.next());
				}
			}
		}
	}

}
