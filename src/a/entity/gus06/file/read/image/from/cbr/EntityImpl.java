package a.entity.gus06.file.read.image.from.cbr;

import java.io.File;
import a.framework.*;
import java.io.InputStream;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201204";}

	
//	private Service buildHolder;
//	private Service readImage;
	
	public EntityImpl() throws Exception
	{
//		buildHolder = Outside.service(this,"gus06.zzz.file.rar.innosystec.holder");
//		readImage = Outside.service(this,"gus06.convert.inputstreamtobufferedimage");
	}
	

	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		if(!file.exists()) return null;
		
//		Object holder = buildHolder.t(file);
//		List list = (List) ((G)holder).g();
//		
//		for(int i=0;i<list.size();i++)
//		{
//			String entry = (String) list.get(i);
//			if(!entry.endsWith("/")) return readImage(holder,entry);
//		}
		return null;
	}
	
	
//	private Object readImage(Object holder, String entry) throws Exception
//	{
//		
//		InputStream is = (InputStream) ((R)holder).r(entry);
//		Object image = readImage.t(is);
//		is.close();
//		((E)holder).e();
//		return image;
//	}
}