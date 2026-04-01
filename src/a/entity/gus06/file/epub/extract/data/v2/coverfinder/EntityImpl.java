package a.entity.gus06.file.epub.extract.data.v2.coverfinder;

import a.framework.*;
import java.util.zip.ZipFile;
import java.util.Map;
import java.awt.image.BufferedImage;
import java.util.zip.ZipEntry;
import java.io.InputStream;
import java.util.Set;
import java.util.Iterator;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20251005";}
	
	private Service isToImage;
	
	public EntityImpl() throws Exception
	{
		isToImage = Outside.service(this,"gus06.convert.inputstreamtobufferedimage");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		ZipFile zipFile = (ZipFile) o[0];
		Map entryMap = (Map) o[1];
		String coverPath = (String) o[2];
		
		BufferedImage image = null;
		if(coverPath!=null) image = entryAsImage(zipFile, entryMap, coverPath);
		if(image==null)
		{
			coverPath = searchForCoverPath(entryMap.keySet());
			if(coverPath!=null) image = entryAsImage(zipFile, entryMap, coverPath);
		}
		Map output = new HashMap();
		output.put("coverImage",image);
		output.put("coverPath",coverPath);
		
		return output;
	}
	
	private BufferedImage entryAsImage(ZipFile zipFile, Map entryMap, String name) throws Exception
	{
		try
		{
			ZipEntry entry = (ZipEntry) entryMap.get(name);
			InputStream is = zipFile.getInputStream(entry);
			return (BufferedImage) isToImage.t(is);
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	private String searchForCoverPath(Set set)
	{
		Iterator it = set.iterator();
		while(it.hasNext())
		{
			String path = ((String) it.next());
			String path_ = path.toLowerCase();
			
			if(path_.endsWith("/cover.jpg")) return path;
			if(path_.endsWith("/cover.jpeg")) return path;
			if(path_.endsWith("/cover.png")) return path;
			if(path_.endsWith("/cover.gif")) return path;
			if(path_.endsWith("/cover.bmp")) return path;
		}
		it = set.iterator();
		while(it.hasNext())
		{
			String path = ((String) it.next());
			String path_ = path.toLowerCase();
			
			if(isImagePath(path_) && path_.contains("cover")) return path;
		}
		return null;
	}
	
	private boolean isImagePath(String path)
	{
		if(path.endsWith(".jpg")) return true;
		if(path.endsWith(".jpeg")) return true;
		if(path.endsWith(".png")) return true;
		if(path.endsWith(".gif")) return true;
		if(path.endsWith(".bmp")) return true;
		return false;
	}
}
