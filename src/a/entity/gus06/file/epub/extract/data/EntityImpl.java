package a.entity.gus06.file.epub.extract.data;

import a.framework.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipFile;
import java.util.zip.ZipEntry;
import java.io.InputStream;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.List;
import java.awt.image.BufferedImage;
import java.util.Set;
import java.util.Iterator;

public class EntityImpl implements Entity,T {

	public String creationDate() {return "20191009";}
	
	public static final String CONTAINER_PATH = "META-INF/container.xml";
	
	
	private Pattern p_path = Pattern.compile("full-path=\"([^\"]+)\"");
	private Pattern p_dc = Pattern.compile("<dc:([^>]+)>([^<]+)</dc:([^>]+)>");
	private Pattern p_reference = Pattern.compile("<reference ([^>]+)>");
	private Pattern p_meta = Pattern.compile("<meta ([^>]+)>");
	private Pattern p_item = Pattern.compile("<item ([^>]+)>");
	
	private Pattern p_name = Pattern.compile("name=\"([^\"]+)\"");
	private Pattern p_content = Pattern.compile("content=\"([^\"]+)\"");
	private Pattern p_href = Pattern.compile("href=\"([^\"]+)\"");
	private Pattern p_type = Pattern.compile("type=\"([^\"]+)\"");
	private Pattern p_id = Pattern.compile("id=\"([^\"]+)\"");



	private Service buildZipFile;
	private Service isToString;
	private Service isToImage;
	private Service buildEntryMap;
	
	public EntityImpl() throws Exception
	{
		buildZipFile = Outside.service(this,"gus06.file.zip.build.zipfile");
		isToString = Outside.service(this,"gus06.io.transfer.tostring.utf8");
		isToImage = Outside.service(this,"gus06.convert.inputstreamtobufferedimage");
		buildEntryMap = Outside.service(this,"gus06.file.zip.zipfile.entrymap");
	}



	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		
		if(file==null) return null;
		if(!file.exists()) return null;
		if(file.length()==0) return null;
		
		Map map = new HashMap();
		try {perform(file, map);}
		catch(Exception e)
		{
			String message = "Failed to extract epub data from file: "+file;
			throw new Exception(message,e);
		}
		return map;
	}
	
	
	
	
	private void perform(File file, Map map) throws Exception
	{
		try
		{
			ZipFile zipFile = (ZipFile) buildZipFile.t(file);
			Map entryMap = (Map) buildEntryMap.t(zipFile);
			
			String container = entryAsString(zipFile, entryMap, CONTAINER_PATH);
			
			Matcher m_path = p_path.matcher(container);
			if(!m_path.find()) throw new Exception("Invalid container.xml data: full-path not found");
			String fullPath = m_path.group(1);
			
			String content = entryAsString(zipFile, entryMap, fullPath);
			String root = findRoot(fullPath);
			
			Matcher m_dc = p_dc.matcher(content);
			Map map_dc = new HashMap();
			while(m_dc.find())
			{
				String key = m_dc.group(1);
				String value = m_dc.group(2);
				map_dc.put(key, value);
			}
			
			Matcher m_meta = p_meta.matcher(content);
			Map map_meta = new HashMap();
			while(m_meta.find())
			{
				String metaAttr = m_meta.group(1);
				String key = extract(metaAttr,p_name);
				String value = extract(metaAttr,p_content);
				
				if(key!=null && value!=null)
				map_meta.put(key, value);
			}
			
			Matcher m_item = p_item.matcher(content);
			Map map_item = new HashMap();
			while(m_item.find())
			{
				String itemAttr = m_item.group(1);
				String key = extract(itemAttr,p_id);
				String value = extract(itemAttr,p_href);
				map_item.put(key, value);
			}
			
			Matcher m_reference = p_reference.matcher(content);
			Map map_reference = new HashMap();
			while(m_reference.find())
			{
				String refAttr = m_reference.group(1);
				String key = extract(refAttr,p_type);
				String value = extract(refAttr,p_href);
				map_reference.put(key, value);
			}
			
			String coverId = get1(map_meta,"cover");
			if(coverId==null) coverId = "cover";
			
			String coverPath0 = null;
			if(coverId!=null) coverPath0 = get1(map_item, coverId);
			if(coverPath0==null) coverPath0 = get1(map_reference, coverId);
			if(coverPath0==null) coverPath0 = coverId;
			
			String coverPath = null;
			if(root!=null && coverPath0!=null) coverPath = root + coverPath0;
			
			BufferedImage cover = null;
			if(coverPath!=null) cover = entryAsImage(zipFile, entryMap, coverPath);
			if(cover==null)
			{
				coverPath = searchForCoverPath(entryMap.keySet());
				if(coverPath!=null) cover = entryAsImage(zipFile, entryMap, coverPath);
			}
			
			map.put("root",root);
			map.put("container",container);
			map.put("fullPath",fullPath);
			map.put("content",content);
			map.put("map_dc",map_dc);
			map.put("map_meta",map_meta);
			map.put("map_item",map_item);
			map.put("coverId",coverId);
			map.put("coverPath0",coverPath0);
			map.put("coverPath",coverPath);
			map.put("cover",cover);
			
			zipFile.close();
		}
		catch(Exception e)
		{
			map.put("exception",e);
			throw e;
		}
	}
	
	
	
	private String entryAsString(ZipFile zipFile, Map entryMap, String name) throws Exception
	{
		try
		{
			ZipEntry entry = (ZipEntry) entryMap.get(name);
			InputStream is = zipFile.getInputStream(entry);
			return (String) isToString.t(is);
		}
		catch(Exception e)
		{throw new Exception("failed to read entry as string: "+name,e);}
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
	
	
	
	private String extract(String s, Pattern p) throws Exception
	{
		Matcher m = p.matcher(s);
		if(!m.find()) return null;
		return m.group(1);
	}
	
	
	private String findRoot(String path)
	{
		if(!path.contains("/")) return "";
		String[] nn = path.split("/");
		StringBuffer b = new StringBuffer();
		for(int i=0;i<nn.length-1;i++)
		{
			b.append(nn[i]);
			b.append("/");
		}
		return b.toString();
	}
	
	
	private String get1(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) return null;
		return (String) map.get(key);
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
